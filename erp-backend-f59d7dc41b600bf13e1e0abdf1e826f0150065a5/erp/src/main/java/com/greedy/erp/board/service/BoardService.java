package com.greedy.erp.board.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.greedy.erp.authority.repository.EmpRepository;
import com.greedy.erp.board.dto.BoardDTO;
import com.greedy.erp.board.entity.Board;
import com.greedy.erp.board.repository.BoardRepository;
import com.greedy.erp.common.Criteria;
import com.greedy.erp.regist.dto.PositionDTO;
import com.greedy.erp.regist.entity.Emp;
import com.greedy.erp.regist.entity.Position;
import com.greedy.erp.util.FileUploadUtils;

@Service
public class BoardService {
	
	private static final Logger log = LoggerFactory.getLogger(BoardService.class);
	private final BoardRepository boardRepository;
	private final ModelMapper modelMapper;
	private final EmpRepository empRepository;
	
	/* 이미지 저장 할 위치 및 응답 할 이미지 주소(WebConfig 설정파일 추가하기) */
	 @Value("${image.image-dir}")
	    private String IMAGE_DIR;
	    @Value("${image.image-url}")
	    private String IMAGE_URL;
	    
	
	@Autowired
	public BoardService( BoardRepository boardRepository, ModelMapper modelMapper,EmpRepository empRepository) {
		this.boardRepository = boardRepository;
		this.modelMapper = modelMapper;
		this.empRepository = empRepository;
	}

	/* 전체 게시판 조회 */
	public int selectBoardTotal() {
		log.info("[BoardService] selectBoardTotal Start ==========");
		List<Board> boardList = boardRepository.findAll();
		log.info("[BoardService] selectBoardTotal End ==========");
		
		return boardList.size();
	}
	
	public Object selectBoard(int boardCode) {
		log.info("[BoardService] selectBoard Start ==========");
		
		Board board = boardRepository.findById(boardCode).get();
		board.setNewFileName(IMAGE_URL + "boardfiles/" + board.getNewFileName());
		
		log.info("[BoardService] selectBoard End ==========");
		
		BoardDTO boardDTO = modelMapper.map(board,BoardDTO.class);
		
		return boardDTO;
	}
	
	
	/* 내 게시판 조회 */

	public int selectMyListTotal(int empCode) {
		log.info("[BoardService] selectMyListTotal Start ==========");
		
		Emp emp = empRepository.findById(empCode).get();
		List<Board> MyBoardList = boardRepository.findByEmpCode(empCode);
		
		log.info("[BoardService] selectMyListTotal End ==========");
		return MyBoardList.size();
	}

	public Object selectMyListWithPaging(Criteria cri, int empCode) {
		log.info("[BoardService] selectBoardListWithPaging Start ==========");

		int index = cri.getPageNum() - 1;
        int count = cri.getAmount(); 
        Pageable paging = PageRequest.of(index, count);
        List<Board> MyBoardList = boardRepository.findByEmpCode(empCode,paging);
        
        log.info("[BoardService] selectBoardListWithPaging End ============");
        
        return MyBoardList.stream().map(board -> modelMapper.map(board, BoardDTO.class)).collect(Collectors.toList());
	}

	
	/* 페이지 처리 */

	public Object selectBoardListWithPaging(Criteria cri) {
		log.info("[BoardService] selectBoardListWithPaging Start ==========");
		
		int index = cri.getPageNum() - 1;
        int count = cri.getAmount(); 
        Pageable paging = PageRequest.of(index, count, Sort.by("boardCode").descending());
        Page<Board> result = boardRepository.findAll(paging);
        List<Board> boardList = (List<Board>)result.getContent();
        
        log.info("[BoardService] selectBoardListWithPaging End ============");
        
        return boardList.stream().map(board -> modelMapper.map(board, BoardDTO.class)).collect(Collectors.toList());
	}

	/* 게시판 등록 */
	@Transactional
	public Object insertBoard(BoardDTO boardDTO, MultipartFile boardImage, int empCode) {
		log.info("[BoardService] insertBoard Start ===================================");
        log.info("[BoardService] boardDTO : " + boardDTO);
        
        String imageName = UUID.randomUUID().toString().replace("-", "");
        String replaceFileName = null;
        Emp emp = empRepository.findById(empCode).get();
       
        int result = 0;

        try {
        	if(boardImage != null) {
        	/* util 패키지에 FileUploadUtils 추가 */
            replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR + "boardfiles", imageName, boardImage);

            boardDTO.setOldFileName((boardImage).getResource().getFilename());
            boardDTO.setNewFileName(replaceFileName);
            log.info("[BoardService] insert File Name : ", replaceFileName);
        	} else {
        		boardDTO.setOldFileName("");
        		boardDTO.setNewFileName("");
        	}

            Board insertBoard = modelMapper.map(boardDTO, Board.class);
            insertBoard.setEmp(emp);
            boardRepository.save(insertBoard);
            result = 1;
        } catch (Exception e) {
            FileUploadUtils.deleteFile(IMAGE_DIR, replaceFileName);
            throw new RuntimeException(e);
        }
        
        return (result > 0) ? "게시판 입력 성공" : "게시판 입력 실패";
	}
	
	
	/* 게시판 수정 */
	@Transactional
	public Object updateBoard(BoardDTO boardDTO, MultipartFile boardImage) {
		log.info("[BoardService] ===== updateBoard Start =====");
		log.info("[BoardService] boardDTO : " + boardDTO);
		
		String replaceFileName = null;
		int result = 0;
		
		try {
			
			
//			Board board = modelMapper.map(boardDTO, Board.class);  // 한번에 전체 등록 매핑
			
			
			Board board= boardRepository.findById(boardDTO.getBoardCode()).get();
			String oriImage = board.getNewFileName();
            log.info("[updateBoard] oriImage : " + oriImage);
            
            /* update를 위한 엔티티 값 수정 */
			board.setBoardCode(boardDTO.getBoardCode());
			board.setBoardTitle(boardDTO.getBoardTitle());
			board.setBoardContent(boardDTO.getBoardContent());
			board.setNoticeYn(boardDTO.getNoticeYn());
			board.setBoardDiv(boardDTO.getBoardDiv());
			board.setExpireDate(board.getExpireDate());
			board.setBoardPwd(boardDTO.getBoardPwd());
			board.setAnonymousYn(board.getAnonymousYn());
			board.setOldFileName(board.getOldFileName());
			board.setNewFileName(board.getNewFileName());
			
			if(boardImage != null){
                String imageName = UUID.randomUUID().toString().replace("-", "");
                replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR , imageName, boardImage);
                log.info("[updateProduct] InsertFileName : " + replaceFileName);
                
                board.setNewFileName(replaceFileName);	// 새로운 파일 이름으로 update
                log.info("[updateProduct] deleteImage : " + oriImage);
                
                boolean isDelete = FileUploadUtils.deleteFile(IMAGE_DIR, oriImage);
                log.info("[update] isDelete : " + isDelete);
            } else {
            	
                /* 이미지 변경 없을 시 */
            	board.setNewFileName(oriImage);
            }
			
			result = 1;
		} catch (Exception e) {
			log.info("[review update] Exception!!");
			FileUploadUtils.deleteFile(IMAGE_DIR, replaceFileName);
            throw new RuntimeException(e);
		}
		
		log.info("[boardService] ====== updatePosition End ======");
		return (result > 0) ? boardDTO : "게시판 수정 실패" ;
	}

	/*검색*/
	public Object searchBoardByTitle(String search) {
		log.info("[boardService] searchBoardByTitle Start =====");
		
 
		List<Board> searchedBoards = boardRepository.findByBoardTitle(search);
		log.info("[boardService] searchBoardByTitle End =======");
		return searchedBoards.stream().map(board -> modelMapper.map(board, BoardDTO.class)).collect(Collectors.toList());
	
	}
	

	



	

//	/* 게시판 삭제 */
//	public Object deleteBoard(Integer boardCode) {
//		log.info("[boardService] ===== deleteBoard Start =====");
//	    
//	    int result = 0;
//	    
//	    try {
//	        boardRepository.deleteById(boardCode);
//	        result = 1;
//	    } catch (Exception e) {
//	        log.info("[position delete] Exception!!");
//	    }
//	    
//	    log.info("[boardService] ====== deleteBoard End ======");
//	    return (result > 0) ? "직급 삭제 성공" : "직급 삭제 실패";
//	}
//
//	public Object deleteBoards(List<Integer> boardCodes) {
//		 boardRepository.deleteAllInBatch(boardRepository.findAllById(boardCodes));
//		    return boardCodes;
//		}

	
	
	

	
	
}
