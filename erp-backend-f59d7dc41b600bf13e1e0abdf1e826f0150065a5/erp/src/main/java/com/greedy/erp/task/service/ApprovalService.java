package com.greedy.erp.task.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.greedy.erp.authority.repository.EmpRepository;
import com.greedy.erp.common.Criteria;
import com.greedy.erp.regist.entity.Emp;
import com.greedy.erp.task.dto.ApprovalDTO;
import com.greedy.erp.task.dto.JoinedApprovalDTO;
import com.greedy.erp.task.entity.Approval;
import com.greedy.erp.task.entity.ApprovalLine;
import com.greedy.erp.task.entity.JoinedApproval;
import com.greedy.erp.task.repository.ApprovalLineRepository;
import com.greedy.erp.task.repository.ApprovalRepository;
import com.greedy.erp.task.repository.JoinedApprovalRepository;
import com.greedy.erp.util.FileUploadUtils;

@Service
public class ApprovalService {

	private static final Logger log = LoggerFactory.getLogger(ApprovalService.class);
	private final ApprovalRepository approvalRepository;
	private final JoinedApprovalRepository joinedApprovalRepository;
	private final ApprovalLineRepository approvalLineRepository;
	private final EmpRepository empRepository;
	private final ModelMapper modelMapper;
	
    @Value("${image.image-dir}")
    private String IMAGE_DIR;
    @Value("${image.image-url}")
    private String IMAGE_URL;
	
	@Autowired
    public ApprovalService(ApprovalRepository approvalRepository, JoinedApprovalRepository joinedApprovalRepository, ModelMapper modelMapper, EmpRepository empRepository, ApprovalLineRepository approvalLineRepository) {
		this.approvalRepository = approvalRepository;
		this.joinedApprovalRepository = joinedApprovalRepository;
		this.approvalLineRepository = approvalLineRepository;
		this.empRepository = empRepository;
		this.modelMapper = modelMapper;
	}

	/* 전체 결재 조회 */
	public int selectApprovalValidTotal() {
		List<JoinedApproval> approvalList = joinedApprovalRepository.findValid();
		return approvalList.size();
	}
	public Object selectApprovalList(Criteria cri) {
		int index = cri.getPageNum() - 1;
        int count = cri.getAmount(); 
        Pageable paging = PageRequest.of(index, count);
        List<JoinedApproval> approvalList = joinedApprovalRepository.findValid(paging);
        return approvalList.stream().map(approval -> modelMapper.map(approval, JoinedApprovalDTO.class)).collect(Collectors.toList());
	}

	/* 결재 하나만 조회 */
	public Object selectApprovalByCode(int approvalCode) {
		JoinedApproval approval = joinedApprovalRepository.findById(approvalCode).get();
		approval.setNewFileName(IMAGE_URL + "approvalfiles/" + approval.getNewFileName());
		return modelMapper.map(approval, JoinedApprovalDTO.class);
	}

	/* 결재 등록 */
	@Transactional
	public Object insertApproval(ApprovalDTO approvalDTO, MultipartFile attachment, List<Integer> approvalLineList) {
		log.info("[ApprovalService] approvalDTO : " + approvalDTO);
		log.info("[ApprovalService] approvalLineList : " + approvalLineList);
        String fileName = UUID.randomUUID().toString().replace("-", "");
        String newFileName = null;
		int result = 0;
		try {	
        	if(attachment != null) {
        		newFileName = FileUploadUtils.saveFile(IMAGE_DIR + "approvalfiles", fileName, attachment);
        		approvalDTO.setOldFileName((attachment).getResource().getFilename());
        		approvalDTO.setNewFileName(newFileName);
        		log.info("[ApprovalService] newFileName : ", newFileName);
        		log.info("[ApprovalService] newFileName : ", newFileName);
        	} else {
        		approvalDTO.setOldFileName("");
        		approvalDTO.setNewFileName("");
        	}
			Approval insertApproval = modelMapper.map(approvalDTO, Approval.class);
			approvalRepository.save(insertApproval);
			result = 1;
			int maxApprovalNum = approvalRepository.findMaxApproval();
	        for(int i = 0 ; i < approvalLineList.size() ; i++) {
	        	ApprovalLine approvalLine = new ApprovalLine();
	        	approvalLine.setApprovalCode(maxApprovalNum);
	        	Emp emp = empRepository.findById(approvalLineList.get(i)).get();
	        	approvalLine.setEmp(emp);
	        	approvalLine.setApproveYn("N");
	        	if(i+1 == approvalLineList.size()) {
	        		approvalLine.setFinalYn("Y");
	        	}else {
	        		approvalLine.setFinalYn("N");
	        	}
	        	approvalLine.setApproverOrder(i+1);
	        	approvalLineRepository.save(approvalLine);
	        }
		} catch (Exception e) {
			log.info("[approval] exception");
		}
		return (result > 0)? "등록 성공" : "등록 실패";
	}
	
	/* 결재 임시저장 */
	@Transactional
	public Object insertDraft(ApprovalDTO approvalDTO, MultipartFile attachment, List<Integer> approvalLineList) {
		log.info("[ApprovalService] approvalDTO : " + approvalDTO);
		log.info("[ApprovalService] approvalLineList : " + approvalLineList);
		String fileName = UUID.randomUUID().toString().replace("-", "");
        String newFileName = null;
		int result = 0;
		try {	
        	if(attachment != null) {
        		newFileName = FileUploadUtils.saveFile(IMAGE_DIR + "approvalfiles", fileName, attachment);
        		approvalDTO.setOldFileName((attachment).getResource().getFilename());
        		approvalDTO.setNewFileName(newFileName);
        		log.info("[ApprovalService] newFileName : ", newFileName);
        		log.info("[ApprovalService] newFileName : ", newFileName);
        	} else {
        		approvalDTO.setOldFileName("");
        		approvalDTO.setNewFileName("");
        	}
			Approval insertApproval = modelMapper.map(approvalDTO, Approval.class);
			approvalRepository.save(insertApproval);
			result = 1;
			if(approvalLineList.size() > 0) {
				int maxApprovalNum = approvalRepository.findMaxApproval();
		        for(int i = 0 ; i < approvalLineList.size() ; i++) {
		        	ApprovalLine approvalLine = new ApprovalLine();
		        	approvalLine.setApprovalCode(maxApprovalNum);
		        	Emp emp = empRepository.findById(approvalLineList.get(i)).get();
		        	approvalLine.setEmp(emp);
		        	approvalLine.setApproveYn("N");
		        	if(i+1 == approvalLineList.size()) {
		        		approvalLine.setFinalYn("Y");
		        	}else {
		        		approvalLine.setFinalYn("N");
		        	}
		        	approvalLine.setApproverOrder(i+1);
		        	approvalLineRepository.save(approvalLine);
		        }
			}

	        
		} catch (Exception e) {
			log.info("[approval] exception");
		}
		return (result > 0)? "임시저장 성공" : "임시저장 실패";
	}

	/* 제목으로 검색 */
	public Object searchApprovalByTitle(String search) {
		String likeSearch = "%" + search + "%";
		List<JoinedApproval> searchedApprovals = joinedApprovalRepository.findValidByApprovalTitleContaining(likeSearch);
		return searchedApprovals.stream().map(approval -> modelMapper.map(approval, JoinedApprovalDTO.class)).collect(Collectors.toList());
	}

	/* 내 결재 조회 */
	public int selectApprovalTotalByEmpCode(int empCode) {
		List<JoinedApproval> approvalList = joinedApprovalRepository.findValidByEmpCode(empCode);
		return approvalList.size();
	}
	public Object selectApprovalListByEmpCode(Criteria cri, int empCode) {
		int index = cri.getPageNum() - 1;
        int count = cri.getAmount(); 
        Pageable paging = PageRequest.of(index, count);
        List<JoinedApproval> approvalList = joinedApprovalRepository.findValidByEmpCode(paging, empCode);
        return approvalList.stream().map(approval -> modelMapper.map(approval, JoinedApprovalDTO.class)).collect(Collectors.toList());
	}
	
	/* 대기중인 결재 조회 */
	public Object selectProcessListByEmpCode(int empCode) {
		List<ApprovalLine> approvalLineList = approvalLineRepository.findProcessByEmpCode(empCode);
		int codeArr[] = new int[approvalLineList.size()];
        for(int i = 0; i < approvalLineList.size(); i++) {     	
        	if( "N".equals(approvalLineList.get(i).getApproveYn()) && approvalLineList.get(i).getApproverOrder() == 1 || "Y".equals(approvalLineRepository.findApprovedYn(approvalLineList.get(i).getApprovalCode(), approvalLineList.get(i).getApproverOrder() - 1)) && "N".equals(approvalLineList.get(i).getApproveYn())  ) {
            	int approvalCode = approvalLineList.get(i).getApprovalCode();
            	codeArr[i] = approvalCode;
        	} else {
        		codeArr[i] = 0;
        	}
        }
        List<JoinedApproval> approvalList = joinedApprovalRepository.findValidByApprovalCodeIn(codeArr);
        return approvalList.stream().map(approval -> modelMapper.map(approval, JoinedApprovalDTO.class)).collect(Collectors.toList());
	}
	
	/* 승인 완료한 결재 조회 */
	public int selectDoneProcessTotalByEmpCode(int empCode) {
		List<ApprovalLine> approvalLineList = approvalLineRepository.findDoneByEmpCode(empCode);
		return approvalLineList.size();
	}
	public Object selectDoneProcessListByEmpCode(Criteria cri, int empCode) {
		int index = cri.getPageNum() - 1;
        int count = cri.getAmount(); 
        Pageable paging = PageRequest.of(index, count);
        List<ApprovalLine> approvalLineList = approvalLineRepository.findDoneByEmpCode(empCode);
        int arr[] = new int[approvalLineList.size()];
        for(int i = 0; i < approvalLineList.size(); i++) {
        	int approvalCode = approvalLineList.get(i).getApprovalCode();
        	arr[i] = approvalCode;
        }
        List<JoinedApproval> approvalList = joinedApprovalRepository.findDoneByApprovalCodeIn(arr, paging);
        return approvalList.stream().map(approval -> modelMapper.map(approval, JoinedApprovalDTO.class)).collect(Collectors.toList());
	}

	/* 임시저장 조회 */
	public int selectDraftTotalByEmpCode(int empCode) {
		List<JoinedApproval> approvalList = joinedApprovalRepository.findDraftByEmpCode(empCode);
		return approvalList.size();
	}
	public Object selectDraftListByEmpCode(Criteria cri, int empCode) {
		int index = cri.getPageNum() - 1;
        int count = cri.getAmount(); 
        Pageable paging = PageRequest.of(index, count);
        List<JoinedApproval> approvalList = joinedApprovalRepository.findDraftByEmpCode(paging, empCode);
        return approvalList.stream().map(approval -> modelMapper.map(approval, JoinedApprovalDTO.class)).collect(Collectors.toList());
	}

	/* 결재 하나 삭제 */
	@Transactional
	public Object deleteApproval(int approvalCode) {
		int result = 0;
	    try {
			approvalLineRepository.deleteByApprovalCode(approvalCode);
			approvalRepository.deleteById(approvalCode);
	        result = 1;
	    } catch (Exception e) {
	        log.info("[approval delete] Exception!!");
	    }
	    return (result > 0) ? "결재 삭제 성공" : "결재 삭제 실패";
	}

	/* 결재 복수 삭제 */
	@Transactional
	public Object deleteApprovals(List<Integer> approvalCodes) {
	for(int i = 0 ; i < approvalCodes.size() ; i++) {
		approvalLineRepository.deleteByApprovalCode(approvalCodes.get(i));
		approvalRepository.deleteById(approvalCodes.get(i));
	}
		return approvalCodes;
	}

	/* 반려 처리 */
	@Transactional
	public Object rejectApproval(int approvalCode) {
		Approval approval = approvalRepository.findById(approvalCode).get();
		approval.setStatusCode(5);
		return modelMapper.map(approval, ApprovalDTO.class);
	}

	/* 결재 수정 */
	@Transactional
	public Object updateApproval(ApprovalDTO approvalDTO, MultipartFile attachment, int approvalCode) throws IOException {
		String fileName = UUID.randomUUID().toString().replace("-", "");
	    String newFileName = null;
		int result = 0;
		Approval newApproval = approvalRepository.findById(approvalCode).get();
		if(attachment != null) {
    		newFileName = FileUploadUtils.saveFile(IMAGE_DIR + "approvalfiles", fileName, attachment);
    		approvalDTO.setOldFileName((attachment).getResource().getFilename());
    		approvalDTO.setNewFileName(newFileName);
    		
			newApproval.setOldFileName(approvalDTO.getOldFileName());
			newApproval.setNewFileName(approvalDTO.getNewFileName());
		} 
		try {

			newApproval.setEmpCode(approvalDTO.getEmpCode());
			newApproval.setStatusCode(approvalDTO.getStatusCode());
//			newApproval.setDocType(approvalDTO.getDocType());
			newApproval.setApprovalDate(new Date(System.currentTimeMillis()));
			newApproval.setApprovalTitle(approvalDTO.getApprovalTitle());
			newApproval.setApprovalContent(approvalDTO.getApprovalContent());

	        result = 1;
		} catch (Exception e) {
			log.info("[approval update] Exception!!");
		}
		return (result > 0) ? "결재 수정 성공" : "결재 수정 실패";
	}

	




}
