package com.greedy.erp.production.stock.controller;

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
import com.greedy.erp.production.stock.dto.StockDTO;
import com.greedy.erp.production.stock.service.StockService;

import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("/api/v1")
public class StockController {	
	private static final Logger log = LoggerFactory.getLogger(StockController.class);
	private final StockService stockService;

	@Autowired
	public StockController(StockService stockService) {
		this.stockService = stockService;
	}
		
	@Operation(summary = "재고 조회", description = "재고 목록 조회", tags = { "StockController" })
	@GetMapping("/stocks")
	public ResponseEntity<ResponseDTO> selectStock(@RequestParam(name = "offset", defaultValue = "1") String offset) {
		log.info("[stockController] selectStock : " + offset);	
		int total = stockService.selectStockTotal();
		Criteria cri = new Criteria(Integer.valueOf(offset), 100);
		PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
		pagingResponseDTO.setData(stockService.selectStockListWithPaging(cri));	
		pagingResponseDTO.setPageInfo(new PageDTO(cri, total));	
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "재고 조회 성공", pagingResponseDTO));	
	}

	@Operation(summary = "재고 상세 조회", description = "재고 상세 조회 진행", tags = { "StockController" })
    @GetMapping("/stock/{stockCode}")
    public ResponseEntity<ResponseDTO> detailStock(@PathVariable int stockCode) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "재고 상세 조회 성공",  stockService.selectStock(stockCode)));
    }
	
//	@Operation(summary = "재고 검색", description = "재고 검색 진행", tags = { "StockController" })
//	@GetMapping("/stocks/search")
//    public ResponseEntity<ResponseDTO> searchStockByType(@RequestParam(name="s", defaultValue="all") String search) {
//
//        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "재고 검색 성공",  stockService.searchStockByType(search)));
//    }
		
	@Operation(summary = "재고 등록", description = "재고 등록 진행", tags = { "StockController" })
    @PostMapping(value = "/stocks")
    public ResponseEntity<ResponseDTO> insertStock(@ModelAttribute StockDTO stockDTO) {
    	return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "재고 등록 성공",  stockService.insertStock(stockDTO)));
    }
	
	@Operation(summary = "재고 수정", description = "재고 수정 진행", tags = { "StockController" })
    @PutMapping("/stocks")
    public ResponseEntity<ResponseDTO> updateStock(@ModelAttribute StockDTO stockDTO) {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "재고 수정 성공", stockService.updateStock(stockDTO)));
    }
	
}
