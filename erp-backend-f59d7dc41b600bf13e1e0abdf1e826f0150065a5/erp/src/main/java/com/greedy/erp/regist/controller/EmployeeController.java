package com.greedy.erp.regist.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
import com.greedy.erp.regist.dto.EmpDTO;
import com.greedy.erp.regist.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

	private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);
	private final EmployeeService employeeService;
	
	
	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	// 로그인한 사원 1명 정보 조회
	@Operation(summary = "사원 1명 조회", description = "사원 1명 조회", tags = { "EmployeeController" })
	@GetMapping("/select/{empCode}")
	public ResponseEntity<ResponseDTO> selectEmpByEmpCode(@PathVariable Integer empCode) {
		log.info("[EmployeeController] 넘어온 empCode 출력 : " + empCode);
		EmpDTO empDTO = (EmpDTO)employeeService.findEmpByEmpCode(empCode);
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "사원 1명 조회 성공", empDTO));	
	}
	
	// 사원 전체 조회
	@Operation(summary = "사원 전체 조회", description = "전체 사원 조회", tags = { "EmployeeController" })
	@GetMapping("/selectAll")
	public ResponseEntity<ResponseDTO> selectAllEmp() {
		log.info("[EmployeeController] 사원 전체 조회 스타트============================");
		
		List<EmpDTO> empList = employeeService.selectAllEmp();
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "전체 사원 조회 성공", empList));	
	}
	
	@Operation(summary = "사원 목록 조회", description = "사원 목록 조회", tags = { "EmployeeController" })
	@GetMapping("/emps")
	public ResponseEntity<ResponseDTO> selectEmployeeList(@RequestParam(name = "offset", defaultValue = "1") String offset) {
		log.info("[EmployeeController] 입력받은 offset 출력 : " + offset);
		
		int total = employeeService.selectEmployeeTotal();
		log.info("[EmployeeController] total 값 출력 : " + total);
		
		Criteria cri = new Criteria(Integer.valueOf(offset), 10);
		PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
		pagingResponseDTO.setData(employeeService.selectEmployeeListWithPaging(cri));	
		pagingResponseDTO.setPageInfo(new PageDTO(cri, total));
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "창고 조회 성공", pagingResponseDTO));	
	}
	
	@Operation(summary = "사원 등록", description = "사원 등록 진행", tags = { "EmployeeController" })
    @PostMapping(value = "/emps")
    public ResponseEntity<ResponseDTO> insertEmployee(@RequestBody EmpDTO empDTO) {
    	return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "창고 등록 성공",  employeeService.insertEmployee(empDTO)));
    }
	
	@Operation(summary = "사원 수정", description = "사원 수정 진행", tags = { "EmployeeController" })
    @PutMapping(value = "/emps")
    public ResponseEntity<ResponseDTO> modifyEmployee(@RequestBody EmpDTO empDTO) {
    	return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "사원 수정 성공",  employeeService.modifyEmployee(empDTO)));
    }
	
	/* 테마 색상 수정 */
	@Operation(summary = "테마 수정", description = "테마 수정이 진행됩니다.", tags = { "EmployeeController" })
    @PatchMapping("/theme/{empCode}")
    public ResponseEntity<ResponseDTO> updateTheme(@PathVariable int empCode, @RequestBody String color) {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "테마 수정 성공", employeeService.updateTheme(empCode, color)));
    }
	
	/* 도장 수정 */
	@Operation(summary = "도장 수정", description = "도장 수정이 진행됩니다.", tags = { "EmployeeController" })
    @PatchMapping("/stamp/{empCode}")
    public ResponseEntity<ResponseDTO> updateStamp(@PathVariable int empCode, MultipartFile stampImage) {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "도장 수정 성공", employeeService.updateStamp(empCode, stampImage)));
    }
	
	/* 사원 사진 수정 */
	@Operation(summary = "사원 사진 수정", description = "사원 사진 수정이 진행됩니다.", tags = { "EmployeeController" })
    @PatchMapping("/changeImg/{empCode}")
    public ResponseEntity<ResponseDTO> updateEmpImg(@PathVariable int empCode, MultipartFile employeeImg) {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "사원 사진 수정 성공", employeeService.updateEmpImg(empCode, employeeImg)));
    }
	
	
}
