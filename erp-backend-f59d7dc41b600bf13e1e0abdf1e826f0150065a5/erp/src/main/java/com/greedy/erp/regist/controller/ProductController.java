package com.greedy.erp.regist.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.greedy.erp.common.Criteria;
import com.greedy.erp.common.PageDTO;
import com.greedy.erp.common.PagingResponseDTO;
import com.greedy.erp.common.ResponseDTO;
import com.greedy.erp.regist.dto.ProductDTO;
import com.greedy.erp.regist.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("api/v1")
public class ProductController {

	private static final Logger log = LoggerFactory.getLogger(ProductController.class);

	private final ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	/* 상품 전체 조회 */
	@Operation(summary = "상품 리스트 조회 요청", description = "상품 조회 및 페이징 처리가 진행됩니다.", tags = { "ProductController" })
	@GetMapping("/products")
	public ResponseEntity<ResponseDTO> selectProductListWithPaging(
			@RequestParam(name = "offset", defaultValue = "1") String offset) {

		log.info("[ProductController] selectProductListWithPaging : " + offset);

		int total = productService.selectProductTotal();	// 상품 전체 개수를 가져옴
		
		// Criteria(pageNum, amount) : 보고싶은 페이지 번호와 한 페이지당 게시글 수를 의미
		Criteria cri = new Criteria(Integer.valueOf(offset), 10);		
		PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
		
		/* 1. offset의 번호에 맞는 페이지에 뿌릴 Product들 */
		pagingResponseDTO.setData(productService.selectProductListWithPaging(cri));
		
		/* 2. PageDTO(Criteria(보고싶은 페이지, 한페이지에 뿌릴 개수), 전체 상품 수) : 화면에서 페이징 처리에 필요한 개념들을 더 계산해서 추출함 */
		pagingResponseDTO.setPageInfo(new PageDTO(cri, total));

		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", pagingResponseDTO));
	}
	
	
	/* 상품 등록 */
	@Operation(summary = "상품 등록 요청", description = "해당 상품 등록이 진행됩니다.", tags = { "ProductController" })
    @PostMapping(value = "/products")
	public ResponseEntity<ResponseDTO> insertProduct(@ModelAttribute ProductDTO productDTO, MultipartFile productImage) {
   
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "상품 입력 성공",  productService.insertProduct(productDTO, productImage)));
    }
	
	/* 상품 수정 */
	@Operation(summary = "상품 수정 요청", description = "해당 상품 수정이 진행됩니다.", tags = { "ProductController" })
    @PutMapping(value = "/products")
    public ResponseEntity<ResponseDTO> updateProduct(@ModelAttribute ProductDTO productDTO,  MultipartFile productImage) {

		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "상품 수정 성공",  productService.updateProduct(productDTO, productImage)));
    }
	
	/* 상품 삭제 */
	@Operation(summary = "상품 삭제", description = "상품 삭제 진행", tags = { "productController" })
	@DeleteMapping("/products/{productCode}")
	public ResponseEntity<ResponseDTO> deleteDept(@PathVariable int productCode) {
	    return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "상품 삭제 성공", productService.deleteProduct(productCode)));
	}
	
	/* 상품 상세 조회 */
	@Operation(summary = "상품 상세 조회", description = "상품 상세 조회 진행", tags = { "ProductController" })
    @GetMapping("/products/{productCode}")
    public ResponseEntity<ResponseDTO> detailProduct(@PathVariable int productCode) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "상품 상세 조회 성공",  productService.selectProduct(productCode)));
    }
	
	/* 검색 상품 리스트 조회 */
	@Operation(summary = "검색 상품 리스트 조회 요청", description = "검색어에 해당되는 상품 리스트 조회가 진행됩니다.", tags = { "ProductController" })
	@GetMapping("/products/search")
    public ResponseEntity<ResponseDTO> selectSearchProductList(@RequestParam(name="s", defaultValue="all") String search) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공",  productService.selectSearchProductList(search)));
    }
	
	
}
