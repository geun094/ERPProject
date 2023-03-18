package com.greedy.erp.task.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.greedy.erp.common.Criteria;
import com.greedy.erp.common.PageDTO;
import com.greedy.erp.common.PagingResponseDTO;
import com.greedy.erp.common.ResponseDTO;
import com.greedy.erp.regist.dto.DeptDTO;
import com.greedy.erp.task.dto.ApprovalDTO;
import com.greedy.erp.task.service.ApprovalService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("api/v1")
public class ApprovalController {
	
	private static final Logger log = LoggerFactory.getLogger(ApprovalController.class);
	private final ApprovalService apporvalService;
	
	@Autowired
	public ApprovalController(ApprovalService apporvalService) {
		this.apporvalService = apporvalService;
	}
	
	/* 전체 결재 조회(임시저장, 삭제 제외) */
	@Operation(summary = "결재리스트 조회 요청", description = "결재리스트 조회 및 페이징 처리가 진행됩니다.", tags = { "ApprovalController" })
	@GetMapping(value = "/approvalList")
	public ResponseEntity<ResponseDTO> selectApprovalListWithPaging(@RequestParam(name = "offset", defaultValue = "1") String offset) {
		log.info("[ApprovalController] selectApprovalListWithPaging : " + offset);
		int total = apporvalService.selectApprovalValidTotal();
		Criteria cri = new Criteria(Integer.valueOf(offset), 10);
		PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
		pagingResponseDTO.setData(apporvalService.selectApprovalList(cri));
		pagingResponseDTO.setPageInfo(new PageDTO(cri, total));
    	return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "전체 결재 조회 성공",  pagingResponseDTO));
    }
	
	/* 결재 하나만 조회 */
	@Operation(summary = "결재 조회 요청", description = "결재 조회가 진행됩니다.", tags = { "ApprovalController" })
	@GetMapping("/approval/{approvalCode}")
	public ResponseEntity<ResponseDTO> selectDeptByCode(@PathVariable int approvalCode) {	
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "결재 조회 성공", apporvalService.selectApprovalByCode(approvalCode)));
	}
	
	/* 결재 등록(기안서 작성) */
	@Operation(summary = "결재 등록 요청", description = "해당 기안서의 결재 등록이 진행됩니다.", tags = { "ApprovalController" })
	@PostMapping(value = "/registApproval/{approvalLineList}")
	public ResponseEntity<ResponseDTO> insertApproval(@ModelAttribute ApprovalDTO approvalDTO, MultipartFile attachment, @PathVariable List<Integer> approvalLineList) {
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "결재 등록 성공",  apporvalService.insertApproval(approvalDTO, attachment, approvalLineList)));
    }
	
	/* 결재 임시저장 */
	@Operation(summary = "결재 임시저장 요청", description = "해당 기안서의 임시저장 등록이 진행됩니다.", tags = { "ApprovalController" })
	@PostMapping(value = "/draftApproval/{approvalLineList}")
	public ResponseEntity<ResponseDTO> insertDraft(@ModelAttribute ApprovalDTO approvalDTO, MultipartFile attachment, @PathVariable List<Integer> approvalLineList) {
		log.info("★★★★★★★★★★★");
		log.info(approvalLineList + "");
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "임시저장 성공",  apporvalService.insertDraft(approvalDTO, attachment, approvalLineList)));
    }
	
	/* 제목으로 검색 */
	@Operation(summary = "결재 검색", description = "제목으로 검색을 진행합니다.", tags = { "ApprovalController" })
	@GetMapping("/approval/search")
    public ResponseEntity<ResponseDTO> searchDeptByName(@RequestParam(name="s", defaultValue="all") String search) {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "결재 검색 성공",  apporvalService.searchApprovalByTitle(search)));
    }

	/* 내 결재보기 */
	@Operation(summary = "내 결재리스트 조회 요청", description = "내 결재리스트 조회 및 페이징 처리가 진행됩니다.", tags = { "ApprovalController" })
	@GetMapping(value = "/approvalList/{empCode}")
	public ResponseEntity<ResponseDTO> selectApprovalListByEmpCode(@PathVariable int empCode, @RequestParam(name = "offset", defaultValue = "1") String offset) {
		log.info("[ApprovalController] selectApprovalListByEmpCode : " + offset + empCode);
		int total = apporvalService.selectApprovalTotalByEmpCode(empCode);
		Criteria cri = new Criteria(Integer.valueOf(offset), 10);
		PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
		pagingResponseDTO.setData(apporvalService.selectApprovalListByEmpCode(cri, empCode));
		pagingResponseDTO.setPageInfo(new PageDTO(cri, total));
    	return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "내 결재 조회 성공",  pagingResponseDTO));
    }
	
	/* 대기중인 결재 조회 */
	@Operation(summary = "대기중인 결재 조회 요청", description = "대기중인 결재 조회 및 페이징 처리가 진행됩니다.", tags = { "ApprovalController" })
	@GetMapping(value = "/process/{empCode}")
	public ResponseEntity<ResponseDTO> selectProcessListByEmpCode(@PathVariable int empCode) {
    	return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "대기중인 조회 성공", apporvalService.selectProcessListByEmpCode(empCode)));
    }
	
	/* 내가 승인한 결재 조회 */
	@Operation(summary = "내가 승인한 결재 조회 요청", description = "내가 승인한 결재 조회 및 페이징 처리가 진행됩니다.", tags = { "ApprovalController" })
	@GetMapping(value = "/doneProcess/{empCode}")
	public ResponseEntity<ResponseDTO> selectDoneProcessListByEmpCode(@PathVariable int empCode, @RequestParam(name = "offset", defaultValue = "1") String offset) {
		log.info("[ApprovalController] selectProcessListByEmpCode : " + offset + empCode);
		int total = apporvalService.selectDoneProcessTotalByEmpCode(empCode);
		Criteria cri = new Criteria(Integer.valueOf(offset), 10);
		PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
		pagingResponseDTO.setData(apporvalService.selectDoneProcessListByEmpCode(cri, empCode));
		pagingResponseDTO.setPageInfo(new PageDTO(cri, total));
    	return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "내가 승인한 결재 조회 성공",  pagingResponseDTO));
    }
	
	/* 내 임시저장 문서 */
	@Operation(summary = "임시저장 문서 조회 요청", description = "임시저장 리스트 조회 및 페이징 처리가 진행됩니다.", tags = { "ApprovalController" })
	@GetMapping(value = "/draftList/{empCode}")
	public ResponseEntity<ResponseDTO> selectDraftListByEmpCode(@PathVariable int empCode, @RequestParam(name = "offset", defaultValue = "1") String offset) {
		log.info("[ApprovalController] selectDraftListByEmpCode : " + offset + empCode);
		int total = apporvalService.selectDraftTotalByEmpCode(empCode);
		Criteria cri = new Criteria(Integer.valueOf(offset), 15);
		PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
		pagingResponseDTO.setData(apporvalService.selectDraftListByEmpCode(cri, empCode));
		pagingResponseDTO.setPageInfo(new PageDTO(cri, total));
    	return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "내 임시저장 문서 조회 성공",  pagingResponseDTO));
    }
	
	/* 결재 하나만 삭제 */
	@Operation(summary = "결재 삭제", description = "결재 삭제가 진행됩니다.", tags = { "ApprovalController" })
	@DeleteMapping("/approval/{approvalCode}")
	public ResponseEntity<ResponseDTO> deleteApproval(@PathVariable int approvalCode) {
	    return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "결재 삭제 성공" , apporvalService.deleteApproval(approvalCode)));
	}
	
	/* 결재 복수 삭제 */
	@Operation(summary = "결재 복수 삭제", description = "결재 부서 삭제가 진행됩니다.", tags = { "ApprovalController" })
	@DeleteMapping("/approvalCodes")
	public ResponseEntity<ResponseDTO> deleteApprovals(@RequestBody List<Integer> approvalCodes) {
	    return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "결재 복수 삭제 성공" , apporvalService.deleteApprovals(approvalCodes)));
	}
	
	/* 반려 처리 */
	@Operation(summary = "결재 반려 처리", description = "결재 반려 처리가 진행됩니다.", tags = { "ApprovalController" })
	@PatchMapping("/rejection/{approvalCode}")
	public ResponseEntity<ResponseDTO> rejectApproval(@PathVariable int approvalCode) {
	    return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "결재 반려 처리 성공" , apporvalService.rejectApproval(approvalCode)));
	}
	
	/* 결재 수정 */
	@Operation(summary = "결재 수정", description = "결재 수정이 진행됩니다.", tags = { "ApprovalController" })
    @PatchMapping("/approval/{approvalCode}")
    public ResponseEntity<ResponseDTO> updateDept(@PathVariable int approvalCode, MultipartFile attachment, @ModelAttribute ApprovalDTO approvalDTO) throws IOException {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "결재 수정 성공", apporvalService.updateApproval(approvalDTO, attachment, approvalCode)));
    }
	
}
