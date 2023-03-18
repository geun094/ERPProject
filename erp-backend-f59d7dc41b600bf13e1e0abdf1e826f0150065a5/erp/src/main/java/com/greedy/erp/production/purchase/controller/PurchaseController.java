package com.greedy.erp.production.purchase.controller;

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
import com.greedy.erp.production.purchase.dto.RequestDTO;
import com.greedy.erp.production.purchase.dto.RequestDetailDTO;
import com.greedy.erp.production.purchase.dto.PlaceDTO;
import com.greedy.erp.production.purchase.dto.PurchaseDTO;
import com.greedy.erp.production.purchase.entity.Request;
import com.greedy.erp.production.purchase.entity.RequestDetail;
import com.greedy.erp.production.purchase.service.PurchaseService;
import com.greedy.erp.regist.dto.StorageDTO;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v1")
public class PurchaseController {
	
	private static final Logger log = LoggerFactory.getLogger(PurchaseController.class);
	
	private final PurchaseService purchaseService;

	@Autowired
	public PurchaseController(PurchaseService PurchaseService) {
		this.purchaseService = PurchaseService;	
	}
		
	/* 요청 */
	@Operation(summary = "요청 조회", description = "요청 목록 조회", tags = { "RequestController" })
	@GetMapping("/requests")
	public ResponseEntity<ResponseDTO> selectRequest(@RequestParam(name = "offset", defaultValue = "1") String offset) {
		log.info("[requestController] selectRequest : " + offset);
		
		int total = purchaseService.selectRequestTotal();
		
		Criteria cri = new Criteria(Integer.valueOf(offset), 10);
		PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
		pagingResponseDTO.setData(purchaseService.selectRequestListWithPaging(cri));	
		pagingResponseDTO.setPageInfo(new PageDTO(cri, total));
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "요청 조회 성공", pagingResponseDTO));	
	}

	@Operation(summary = "요청 상세 조회", description = "요청 상세 조회 진행", tags = { "RequestController" })
    @GetMapping("/request/{requestCode}")
    public ResponseEntity<ResponseDTO> detailRequest(@PathVariable int requestCode) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "요청 상세 조회 성공",  purchaseService.selectRequest(requestCode)));
    }
	
	@Operation(summary = "요청 등록", description = "요청 등록 진행", tags = { "RequestController" })
    @PostMapping(value = "/requests")
    public ResponseEntity<ResponseDTO> insertRequest(@RequestBody RequestDTO requestDTO) {
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "요청 등록 성공",  purchaseService.insertRequest(requestDTO)));
    }
	
	@Operation(summary = "요청 수정", description = "요청 수정 진행", tags = { "RequestController" })
    @PutMapping("/requests")
    public ResponseEntity<ResponseDTO> updateRequest(@RequestBody RequestDTO requestDTO) {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "요청 수정 성공", purchaseService.updateRequest(requestDTO)));
    }
	
	
	/* 발주 */
	@Operation(summary = "발주 조회", description = "발주 목록 조회", tags = { "PlaceController" })
	@GetMapping("/places")
	public ResponseEntity<ResponseDTO> selectPlace(@RequestParam(name = "offset", defaultValue = "1") String offset) {
		log.info("[placeController] selectPlace : " + offset);
		int total = purchaseService.selectPlaceTotal();
		Criteria cri = new Criteria(Integer.valueOf(offset), 10);
		PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
		pagingResponseDTO.setData(purchaseService.selectPlaceListWithPaging(cri));	
		pagingResponseDTO.setPageInfo(new PageDTO(cri, total));
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "발주 조회 성공", pagingResponseDTO));	
	}

	@Operation(summary = "발주 상세 조회", description = "발주 상세 조회 진행", tags = { "PlaceController" })
    @GetMapping("/place/{placeCode}")
    public ResponseEntity<ResponseDTO> detailPlace(@PathVariable int placeCode) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "발주 상세 조회 성공", purchaseService.selectPlace(placeCode)));
    }
	
	@Operation(summary = "발주 등록", description = "발주 등록 진행", tags = { "PlaceController" })
    @PostMapping(value = "/places")
    public ResponseEntity<ResponseDTO> insertOrders(@RequestBody PlaceDTO placeDTO) {
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "발주 등록 성공", purchaseService.insertPlace(placeDTO)));
    }
	
	@Operation(summary = "발주 수정", description = "발주 수정 진행", tags = { "PlaceController" })
    @PutMapping("/places")
    public ResponseEntity<ResponseDTO> updatePlace(@RequestBody PlaceDTO placeDTO) {
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "발주 수정 성공", purchaseService.updatePlace(placeDTO)));
    }
	
	
	/* 판매 */
	@Operation(summary = "판매 조회", description = "판매 목록 조회", tags = { "PurchaseController" })
	@GetMapping("/purchases")
	public ResponseEntity<ResponseDTO> selectPurchase(@RequestParam(name = "offset", defaultValue = "1") String offset) {
		log.info("[purchaseController] selectPurchase : " + offset);
		int total = purchaseService.selectPurchaseTotal();
		Criteria cri = new Criteria(Integer.valueOf(offset), 10);
		PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
		pagingResponseDTO.setData(purchaseService.selectPurchaseListWithPaging(cri));	
		pagingResponseDTO.setPageInfo(new PageDTO(cri, total));
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "판매 조회 성공", pagingResponseDTO));	
	}

	@Operation(summary = "판매 상세 조회", description = "판매 상세 조회 진행", tags = { "PurchaseController" })
    @GetMapping("/purchase/{purchaseCode}")
    public ResponseEntity<ResponseDTO> detailPurchase(@PathVariable int purchaseCode) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "판매 상세 조회 성공", purchaseService.selectPurchase(purchaseCode)));
    }
	
	@Operation(summary = "판매 등록", description = "판매 등록 진행", tags = { "SalesController" })
    @PostMapping(value = "/purchases")
    public ResponseEntity<ResponseDTO> insertSales(@RequestBody PurchaseDTO purchaseDTO) {
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "판매 등록 성공", purchaseService.insertPurchase(purchaseDTO)));
    }
	
}
