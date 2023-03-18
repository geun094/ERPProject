package com.greedy.erp.production.business.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
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

import com.greedy.erp.common.Criteria;
import com.greedy.erp.common.PageDTO;
import com.greedy.erp.common.PagingResponseDTO;
import com.greedy.erp.common.ResponseDTO;
import com.greedy.erp.production.business.dto.EstimateDTO;
import com.greedy.erp.production.business.dto.EstimateDetailDTO;
import com.greedy.erp.production.business.dto.OrdersDTO;
import com.greedy.erp.production.business.dto.SalesDTO;
import com.greedy.erp.production.business.entity.Estimate;
import com.greedy.erp.production.business.entity.EstimateDetail;
import com.greedy.erp.production.business.service.BusinessService;
import com.greedy.erp.regist.dto.StorageDTO;

import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("/api/v1")
public class BusinessController {
	
	private static final Logger log = LoggerFactory.getLogger(BusinessController.class);
	
	private final BusinessService businessService;

	@Autowired
	public BusinessController(BusinessService BusinessService) {
		this.businessService = BusinessService;	
	}
		
	/* 견적 */
	@Operation(summary = "견적 조회", description = "견적 목록 조회", tags = { "EstimateController" })
	@GetMapping("/estimates")
	public ResponseEntity<ResponseDTO> selectEstimate(@RequestParam(name = "offset", defaultValue = "1") String offset) {
		log.info("[estimateController] selectEstimate : " + offset);
		
		int total = businessService.selectEstimateTotal();
		
		Criteria cri = new Criteria(Integer.valueOf(offset), 10);
		PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
		pagingResponseDTO.setData(businessService.selectEstimateListWithPaging(cri));	
		pagingResponseDTO.setPageInfo(new PageDTO(cri, total));
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "견적 조회 성공", pagingResponseDTO));	
	}

	@Operation(summary = "견적 상세 조회", description = "견적 상세 조회 진행", tags = { "EstimateController" })
    @GetMapping("/estimate/{estimateCode}")
    public ResponseEntity<ResponseDTO> detailEstimate(@PathVariable int estimateCode) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "견적 상세 조회 성공",  businessService.selectEstimate(estimateCode)));
    }
	
	@Operation(summary = "견적 등록", description = "견적 등록 진행", tags = { "EstimateController" })
    @PostMapping(value = "/estimates")
    public ResponseEntity<ResponseDTO> insertEstimate(@RequestBody EstimateDTO estimateDTO) {
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "견적 등록 성공",  businessService.insertEstimate(estimateDTO)));
    }
	
	@Operation(summary = "견적 수정", description = "견적 수정 진행", tags = { "EstimateController" })
    @PutMapping("/estimates")
    public ResponseEntity<ResponseDTO> updateEstimate(@RequestBody EstimateDTO estimateDTO) {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "견적 수정 성공", businessService.updateEstimate(estimateDTO)));
    }
	
	
	/* 주문 */
	@Operation(summary = "주문 조회", description = "주문 목록 조회", tags = { "OrdersController" })
	@GetMapping("/orders")
	public ResponseEntity<ResponseDTO> selectOrders(@RequestParam(name = "offset", defaultValue = "1") String offset) {
		log.info("[ordersController] selectOrders : " + offset);
		int total = businessService.selectOrdersTotal();
		Criteria cri = new Criteria(Integer.valueOf(offset), 10);
		PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
		pagingResponseDTO.setData(businessService.selectOrdersListWithPaging(cri));	
		pagingResponseDTO.setPageInfo(new PageDTO(cri, total));
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "주문 조회 성공", pagingResponseDTO));	
	}

	@Operation(summary = "주문 상세 조회", description = "주문 상세 조회 진행", tags = { "OrdersController" })
    @GetMapping("/order/{ordersCode}")
    public ResponseEntity<ResponseDTO> detailOrders(@PathVariable int ordersCode) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "주문 상세 조회 성공", businessService.selectOrders(ordersCode)));
    }
	
	@Operation(summary = "주문 등록", description = "주문 등록 진행", tags = { "OrdersController" })
    @PostMapping(value = "/orders")
    public ResponseEntity<ResponseDTO> insertOrders(@RequestBody OrdersDTO ordersDTO) {
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "주문 등록 성공", businessService.insertOrders(ordersDTO)));
    }
	
	@Operation(summary = "주문 수정", description = "주문 수정 진행", tags = { "OrdersController" })
    @PutMapping("/orders")
    public ResponseEntity<ResponseDTO> updateOrders(@RequestBody OrdersDTO ordersDTO) {
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "주문 수정 성공", businessService.updateOrders(ordersDTO)));
    }
	
	
	
	/* 판매 */
	@Operation(summary = "판매 조회", description = "판매 목록 조회", tags = { "SalesController" })
	@GetMapping("/sales")
	public ResponseEntity<ResponseDTO> selectSales(@RequestParam(name = "offset", defaultValue = "1") String offset) {
		log.info("[salesController] selectSales : " + offset);
		int total = businessService.selectSalesTotal();
		Criteria cri = new Criteria(Integer.valueOf(offset), 10);
		PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
		pagingResponseDTO.setData(businessService.selectSalesListWithPaging(cri));	
		pagingResponseDTO.setPageInfo(new PageDTO(cri, total));
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "판매 조회 성공", pagingResponseDTO));	
	}

	@Operation(summary = "판매 상세 조회", description = "판매 상세 조회 진행", tags = { "SalesController" })
    @GetMapping("/sale/{salesCode}")
    public ResponseEntity<ResponseDTO> detailSales(@PathVariable int salesCode) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "판매 상세 조회 성공", businessService.selectSales(salesCode)));
    }
	
	@Operation(summary = "판매 등록", description = "판매 등록 진행", tags = { "SalesController" })
    @PostMapping(value = "/sales")
    public ResponseEntity<ResponseDTO> insertSales(@RequestBody SalesDTO salesDTO) {
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "판매 등록 성공", businessService.insertSales(salesDTO)));
    }
	
	@Operation(summary = "판매 수정", description = "판매 수정 진행", tags = { "SalesController" })
    @PutMapping("/sales")
    public ResponseEntity<ResponseDTO> updateSales(@RequestBody SalesDTO salesDTO) {
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "판매 수정 성공", businessService.updateSales(salesDTO)));
    }

}
