package com.greedy.erp.regist.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greedy.erp.common.Criteria;
import com.greedy.erp.common.PageDTO;
import com.greedy.erp.common.PagingResponseDTO;
import com.greedy.erp.common.ResponseDTO;
import com.greedy.erp.regist.dto.DeptDTO;
import com.greedy.erp.regist.dto.EmpDTO;
import com.greedy.erp.regist.service.DeptService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("api/v1")
public class DeptController {
	
	private static final Logger log = LoggerFactory.getLogger(DeptController.class);
	private final DeptService deptService;
	
	@Autowired
	public DeptController(DeptService deptService) {
		this.deptService = deptService;
	}
	
	/* 전체 부서 조회 */
	@Operation(summary = "부서 리스트 조회 요청", description = "부서 리스트 조회가 진행됩니다.", tags = { "DeptController" })
	@GetMapping("/deptList")
	public ResponseEntity<ResponseDTO> selectDeptListWithPaging(@RequestParam(name = "offset", defaultValue = "1") String offset) {	
		log.info("[DeptController] selectDeptListWithPaging : " + offset);
		int total = deptService.selectDeptTotal();
		Criteria cri = new Criteria(Integer.valueOf(offset), 10);
		PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
		pagingResponseDTO.setData(deptService.selectDeptList(cri));	
		pagingResponseDTO.setPageInfo(new PageDTO(cri, total));
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "부서 조회 성공", pagingResponseDTO));
	}
	
	/* 페이징 없는 부서 목록(모달용) */
	@Operation(summary = "페이징 없는 부서 목록", description = "페이징 없는 부서목록입니다.", tags = { "DeptController" })
	@GetMapping("/deptSelectList")
	public ResponseEntity<ResponseDTO> selectDeptList() {	
		log.info("[DeptController] selectDeptLis");
		List<DeptDTO> deptList = deptService.selectAllDept();
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "부서 조회 성공", deptList));
	}

	/* 부서 하나만 조회 */
	@Operation(summary = "부서 조회 요청", description = "부서 조회가 진행됩니다.", tags = { "DeptController" })
	@GetMapping("/dept/{deptCode}")
	public ResponseEntity<ResponseDTO> selectDeptByCode(@PathVariable int deptCode) {	
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "부서 조회 성공", deptService.selectDeptByCode(deptCode)));
	}
	
	/* 신규 부서 등록 */
	@Operation(summary = "부서 등록 요청", description = "해당 부서 등록이 진행됩니다.", tags = { "DeptController" })
    @PostMapping(value = "/registDept")
    public ResponseEntity<ResponseDTO> insertNewDept(@ModelAttribute DeptDTO deptDTO) {
    	
    	return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "부서 입력 성공",  deptService.insertNewDept(deptDTO)));
    }
	
	/* 부서 수정 */
	@Operation(summary = "부서 수정", description = "부서 수정이 진행됩니다.", tags = { "DeptController" })
    @PutMapping("/dept")
    public ResponseEntity<ResponseDTO> updateDept(@ModelAttribute DeptDTO deptDTO) {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "부서 수정 성공", deptService.updateDept(deptDTO)));
    }
	
	/* 부서 삭제 */
	@Operation(summary = "부서 삭제", description = "부서 삭제가 진행됩니다.", tags = { "DeptController" })
	@DeleteMapping("/dept/{deptCode}")
	public ResponseEntity<ResponseDTO> deleteDept(@PathVariable int deptCode) {
	    return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "부서 삭제 성공" , deptService.deleteDept(deptCode)));
	}
	
	/* 부서 복수 삭제 */
	@Operation(summary = "부서 복수 삭제", description = "부서 복수 삭제가 진행됩니다.", tags = { "DeptController" })
	@DeleteMapping("/deptCodes")
	public ResponseEntity<ResponseDTO> deleteDepts(@RequestBody List<Integer> deptCodes) {
	    return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "부서 복수 삭제 성공" , deptService.deleteDepts(deptCodes)));
	}

	/* 전체 부서코드 조회 */
	@Operation(summary = "전체 부서코드 조회", description = "전체 부서코드 조회합니다.", tags = { "DeptController" })
	@GetMapping("/deptCodes")
	public ResponseEntity<ResponseDTO> selectDeptCodes() {
	    return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "전체 부서코드 조회 성공" , deptService.selectDeptCodes()));
	}
	
	/* 부서명으로 검색 */
	@Operation(summary = "부서 검색", description = "부서명으로 검색을 진행합니다.", tags = { "DeptController" })
	@GetMapping("/dept/search")
    public ResponseEntity<ResponseDTO> searchDeptByName(@RequestParam(name="s", defaultValue="all") String search) {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "부서 검색 성공",  deptService.searchDeptByName(search)));
    }
	

}
