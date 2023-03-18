package com.greedy.erp.production.production.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greedy.erp.common.Criteria;
import com.greedy.erp.common.PageDTO;
import com.greedy.erp.common.PagingResponseDTO;
import com.greedy.erp.common.ResponseDTO;
import com.greedy.erp.production.business.dto.OrdersDTO;
import com.greedy.erp.production.production.dto.ForwardingDTO;
import com.greedy.erp.production.production.dto.WorkDTO;
import com.greedy.erp.production.production.service.ProductionService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v1")
public class ProductionController {

	private static final Logger log = LoggerFactory.getLogger(ProductionController.class);
	private final ProductionService productionService;
	
	
	@Autowired
	public ProductionController(ProductionService productionService) {
		this.productionService = productionService;
	}
	
/* <===================================== 작업 관련 컨트롤러 =====================================> */
	
	@Operation(summary = "작업 1개 조회", description = "작업 1개 조회", tags = { "ProductionController" })
	@GetMapping("/works/{workCode}")
	public ResponseEntity<ResponseDTO> selectWorkByWorkCode(@PathVariable Integer workCode) {
		log.info("[ProductionController] 넘어온 workCode 출력 : " + workCode);
		WorkDTO workDTO = productionService.findWorkByWorkCode(workCode);
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "작업 1개 조회 성공", workDTO));	
	}
	
	@Operation(summary = "작업 목록 조회", description = "작업 목록 조회", tags = { "ProductionController" })
	@GetMapping("/works")
	public ResponseEntity<ResponseDTO> selectEmployeeList(@RequestParam(name = "offset", defaultValue = "1") String offset) {
		log.info("[ProductionController] 입력받은 offset 출력 : " + offset);
		
		int total = productionService.selectEmployeeTotal();
		log.info("[ProductionController] total 값 출력 : " + total);
		
		Criteria cri = new Criteria(Integer.valueOf(offset), 10);
		PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
		pagingResponseDTO.setData(productionService.selectWorkListWithPaging(cri));	
		pagingResponseDTO.setPageInfo(new PageDTO(cri, total));
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "작업 목록 조회 성공", pagingResponseDTO));	
	}
	
//	@Operation(summary = "사원 등록", description = "사원 등록 진행", tags = { "EmployeeController" })
//    @PostMapping(value = "/employees")
//    public ResponseEntity<ResponseDTO> insertEmployee(@RequestBody EmpDTO empDTO) {
//    	return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "창고 등록 성공",  employeeService.insertEmployee(empDTO)));
//    }
//	
//	@Operation(summary = "사원 수정", description = "사원 수정 진행", tags = { "EmployeeController" })
//    @PutMapping(value = "/employees")
//    public ResponseEntity<ResponseDTO> modifyEmployee(@RequestBody EmpDTO empDTO) {
//    	return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "사원 수정 성공",  employeeService.modifyEmployee(empDTO)));
//    }
	
	/* <===================================== 출고 관련 컨트롤러 =====================================> */
	@Operation(summary = "출고 1개 조회", description = "출고 1개 조회", tags = { "ProductionController" })
	@GetMapping("/forwardings/{forwardingCode}")
	public ResponseEntity<ResponseDTO> selectForwardingByForwardingCode(@PathVariable Integer forwardingCode) {
		log.info("[ProductionController] 넘어온 forwardingCode 출력 : " + forwardingCode);
		ForwardingDTO forwardingDTO = productionService.findForwardingByForwardingCode(forwardingCode);
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "출고 1개 조회 성공", forwardingDTO));	
	}
	
	@Operation(summary = "출고 목록 조회", description = "출고 목록 조회", tags = { "ProductionController" })
	@GetMapping("/forwardings")
	public ResponseEntity<ResponseDTO> selectForwardingList(@RequestParam(name = "offset", defaultValue = "1") String offset) {
		log.info("[ProductionController] 입력받은 offset 출력 : " + offset);
		
		int total = productionService.selectForwardingTotal();
		log.info("[ProductionController] total 값 출력 : " + total);
		
		Criteria cri = new Criteria(Integer.valueOf(offset), 10);
		PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
		pagingResponseDTO.setData(productionService.selectForwardingListWithPaging(cri));	
		pagingResponseDTO.setPageInfo(new PageDTO(cri, total));
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "출고 목록 조회 성공", pagingResponseDTO));	
	}
	
	@Operation(summary = "출고 등록", description = "출고 등록 진행", tags = { "ForwardingController" })
    @PostMapping(value = "/forwardings")
    public ResponseEntity<ResponseDTO> insertOrders(@RequestBody ForwardingDTO forwardingDTO) {
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "출고 등록 성공", productionService.insertForwarding(forwardingDTO)));
    }
	
}
