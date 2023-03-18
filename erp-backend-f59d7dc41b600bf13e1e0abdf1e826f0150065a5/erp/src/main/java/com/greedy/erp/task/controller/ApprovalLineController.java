package com.greedy.erp.task.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greedy.erp.common.Criteria;
import com.greedy.erp.common.PageDTO;
import com.greedy.erp.common.PagingResponseDTO;
import com.greedy.erp.common.ResponseDTO;
import com.greedy.erp.task.service.ApprovalLineService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("api/v1")
public class ApprovalLineController {

	private static final Logger log = LoggerFactory.getLogger(ApprovalController.class);
	private final ApprovalLineService approvalLineService;
	
	@Autowired
	public ApprovalLineController(ApprovalLineService approvalLineService) {
		this.approvalLineService = approvalLineService;
	}
	
	/* 결재 코드로 라인리스트 조회 */
	@Operation(summary = "결재라인 조회 요청", description = "결재라인 조회가 진행됩니다.", tags = { "ApprovalLineController" })
	@GetMapping("/approvalLine/{approvalCode}")
	public ResponseEntity<ResponseDTO> selectDeptByCode(@PathVariable int approvalCode) {	
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "결재라인 조회 성공", approvalLineService.selectApprovalLineByApproval(approvalCode)));
	}
	
	/* 결재 승인하기 */
	@Operation(summary = "승인 여부 변경", description = "승인 여부 변경이 진행됩니다.", tags = { "ApprovalLineController" })
    @PatchMapping("/signoff/{empCode}")
    public ResponseEntity<ResponseDTO> updateApprovalStatus(@PathVariable int empCode, @RequestBody int approvalCode) {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "승인 여부 변경 성공", approvalLineService.updateApproveYn(empCode, approvalCode)));
    }
	
	/* 전체 사원목록 조회 */
	@Operation(summary = "사원 리스트 조회 요청", description = "사원 리스트 조회가 진행됩니다.", tags = { "ApprovalLineController" })
	@GetMapping("/approvers")
	public ResponseEntity<ResponseDTO> selectApproverListWithPaging(@RequestParam(name = "offset", defaultValue = "1") String offset) {	
		log.info("[ApprovalLineController] selectApproverListWithPaging : " + offset);
		int total = approvalLineService.selectApproverTotal();
		Criteria cri = new Criteria(Integer.valueOf(offset), 10);
		PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
		pagingResponseDTO.setData(approvalLineService.selectApproverList(cri));	
		pagingResponseDTO.setPageInfo(new PageDTO(cri, total));
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "사원 리스트 조회 성공", pagingResponseDTO));
	}
	
	/* 사원이름으로 검색 */
	@Operation(summary = "사원 이름으로 검색", description = "사원명으로 검색을 진행합니다.", tags = { "ApprovalLineController" })
	@GetMapping("/approvers/search")
    public ResponseEntity<ResponseDTO> searchApproverByName(@RequestParam(name="s", defaultValue="all") String search) {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "사원 검색 성공", approvalLineService.searchApproverByName(search)));
    }
}






















