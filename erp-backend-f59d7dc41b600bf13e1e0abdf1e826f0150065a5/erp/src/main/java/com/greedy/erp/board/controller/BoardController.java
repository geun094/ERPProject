package com.greedy.erp.board.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.greedy.erp.board.dto.BoardDTO;
import com.greedy.erp.board.service.BoardService;
import com.greedy.erp.common.Criteria;
import com.greedy.erp.common.PageDTO;
import com.greedy.erp.common.PagingResponseDTO;
import com.greedy.erp.common.ResponseDTO;

import io.swagger.v3.oas.annotations.Operation;

@RestController
//@CrossOrigin(origins = "http://localhost:7777")
@RequestMapping("/api/v1")
public class BoardController {

private static final Logger log = LoggerFactory.getLogger(BoardController.class);
	
	private final BoardService boardService;

	@Autowired
	public BoardController(BoardService boardService) {
		this.boardService = boardService;	
	}
	
	/* 전체 게시판 조회 */
	@Operation(summary = "게시판 조회", description = "게시판 조회 및 페이지", tags = { "BoardController" })
	@GetMapping("/boards")
	public ResponseEntity<ResponseDTO> selectBoard(@RequestParam(name = "offset", defaultValue = "1") String offset) {
		log.info("[BoardController] selectBoard : " + offset);
		
		int total = boardService.selectBoardTotal();
		Criteria cri = new Criteria(Integer.valueOf(offset), 10);
		PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
		pagingResponseDTO.setData(boardService.selectBoardListWithPaging(cri));	
		pagingResponseDTO.setPageInfo(new PageDTO(cri, total));
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "게시판 조회 성공", pagingResponseDTO));	
	}
	
	/* 게시판 상세조회*/
	@Operation(summary = "게시판 상세 요청", description = "게시판 상세 조회", tags = { "BoardController" })
    @GetMapping("/boards/{boardCode}")
    public ResponseEntity<ResponseDTO> detailBoard(@PathVariable int boardCode) {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "게시판 상세 조회 성공",  boardService.selectBoard(boardCode)));
    }
	
	/* 사원코드기준으로  게시판리스트 조회 */
	@Operation(summary = "게시판 조회", description = "게시판 조회 및 페이지", tags = { "BoardController" })
	@GetMapping("/myBoards/{empCode}")
	public ResponseEntity<ResponseDTO> selectMyList(@PathVariable int empCode, @RequestParam(name = "offset", defaultValue = "1") String offset) {
		log.info("[BoardController] selectMyList : " + offset);
		
		int total = boardService.selectMyListTotal(empCode);
		
		Criteria cri = new Criteria(Integer.valueOf(offset), 10);
		PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
		pagingResponseDTO.setData(boardService.selectMyListWithPaging(cri,empCode));	
		pagingResponseDTO.setPageInfo(new PageDTO(cri, total));
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "게시판 조회 성공", pagingResponseDTO));	
	}
	
	/* 게시판 등록 */
	@Operation(summary = "게시판 등록 요청", description = "해당 게시판 등록이 진행됩니다.", tags = { "BoardController" })
    @PostMapping(value = "/boards/{empCode}")
    public ResponseEntity<ResponseDTO> insertBoard(@ModelAttribute BoardDTO boardDTO, MultipartFile boardImage,@PathVariable int empCode) {
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "게시판 입력 성공",  boardService.insertBoard(boardDTO, boardImage,empCode)));
    }
	
	/* 게시판 수정 */
	@Operation(summary = "게시판 수정", description = "게시판 수정 진행", tags = { "BoardController" })
    @PutMapping("/boards/{empCode}")
    public ResponseEntity<ResponseDTO> updateBoard(@ModelAttribute BoardDTO boardDTO, MultipartFile boardImage) {
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "게시판 수정 성공", boardService.updateBoard(boardDTO, boardImage)));
    }
	
	/* 검색 */
	@Operation(summary = "게시판 검색", description = "게시판 검색 진행", tags = { "BoardController" })
	@GetMapping("/board/search")
    public ResponseEntity<ResponseDTO> searchBoardByTitle(@RequestParam(name="s", defaultValue="all") String search) {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "게시판 검색 성공",  boardService.searchBoardByTitle(search)));
    }
	
	
//	/* 내게시판 삭제*/
//	
//	@Operation(summary = "게시판 삭제", description = "게시판 삭제 진행", tags = { "BoardController" })
//	@DeleteMapping("/boards/{boardCode}")
//	public ResponseEntity<ResponseDTO> deletePosition(@PathVariable Integer boardCode) {
//	    return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "직급 삭제 성공", boardService.deleteBoard(boardCode)));
//	}
//	
//	@Operation(summary = "복수 게시판 삭제", description = "복수 게시판 삭제 진행", tags = { "BoardController" })
//	@DeleteMapping("/boards")
//    public ResponseEntity<ResponseDTO> deleteboards(@RequestBody List<Integer> boardCodes) {
//        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "복수 게시판 삭제 성공", boardService.deleteBoards(boardCodes)));
//                    
//    }
    
	
	

	
}
