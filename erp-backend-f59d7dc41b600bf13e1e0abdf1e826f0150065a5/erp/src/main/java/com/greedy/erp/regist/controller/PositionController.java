
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
import com.greedy.erp.regist.dto.PositionDTO;
import com.greedy.erp.regist.service.PositionService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
//@CrossOrigin(origins = "http://localhost:7777")
@RequestMapping("/api/v1")
public class PositionController{
	
private static final Logger log = LoggerFactory.getLogger(PositionController.class);
	
	private final PositionService positionService;

	@Autowired
	public PositionController(PositionService positionService) {
		this.positionService = positionService;	
	}
	
	/* 직급 조회 */
	@Operation(summary = "직급 조회", description = "직급 조회 및 페이지", tags = { "PositionController" })
	@GetMapping("/positions")
	public ResponseEntity<ResponseDTO> selectPosition(@RequestParam(name = "offset", defaultValue = "1") String offset) {
		log.info("[PositionController] selectPosition : " + offset);
		
		int total = positionService.selectPositionTotal();
		
		Criteria cri = new Criteria(Integer.valueOf(offset), 10);
		PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
		pagingResponseDTO.setData(positionService.selectPositionListWithPaging(cri));	
		pagingResponseDTO.setPageInfo(new PageDTO(cri, total));
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "직급 조회 성공", pagingResponseDTO));	
	}
	
	@Operation(summary = "직급 상세 요청", description = "지급 상세 조회", tags = { "PositionController" })
    @GetMapping("/positions/{positionCode}")
    public ResponseEntity<ResponseDTO> detailPosition(@PathVariable int positionCode) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "직급 상세 조회 성공",  positionService.selectPosition(positionCode)));
    }
	
	
	/* 직급 등록 */
	
	@Operation(summary = "직급 등록", description = "직급 등록 진행", tags = { "PositionController" })
    @PostMapping(value = "/positions")
    public ResponseEntity<ResponseDTO> insertPosition(@ModelAttribute PositionDTO positionDTO) {
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "직급 등록 성공",  positionService.insertPosition(positionDTO)));
    }
	
	
	/* 직급 수정 */
	@Operation(summary = "직급 수정", description = "직급 수정 진행", tags = { "PositionController" })
    @PutMapping("/positions")
    public ResponseEntity<ResponseDTO> updateStorage(@ModelAttribute PositionDTO positionDTO) {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "직급 수정 성공", positionService.updatePosition(positionDTO)));
    }

	
	/* 직급 삭제*/
	
	@Operation(summary = "직급 삭제", description = "직급 삭제 진행", tags = { "PositionController" })
	@DeleteMapping("/positions/{positionCode}")
	public ResponseEntity<ResponseDTO> deletePosition(@PathVariable Integer positionCode) {
	    return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "직급 삭제 성공", positionService.deletePosition(positionCode)));
	}
	
	@Operation(summary = "복수 직급 삭제", description = "복수 직급 삭제 진행", tags = { "PositionController" })
	@DeleteMapping("/positions")
    public ResponseEntity<ResponseDTO> deletePositions(@RequestBody List<Integer> positionCodes) {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "복수직급 삭제 성공", positionService.deletePositions(positionCodes)));
                    
    }
	
	
	
	/* 직급 전체 조회 */
	@Operation(summary = "직급 전체 조회", description = "직급 사원 조회", tags = { "PositionController" })
	@GetMapping("/selectAllPosition")
	public ResponseEntity<ResponseDTO> selectAllPosition() {
		log.info("[PositionController] 직급 전체 조회 스타트============================");
		
		List<PositionDTO> positionList = positionService.selectAllPosition();
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "전체 wlrrmq 조회 성공", positionList));	
	}
	
	/* 검색 */
	@Operation(summary = "직급 검색", description = "직급 검색 진행", tags = { "PositionController" })
	@GetMapping("/positions/search")
    public ResponseEntity<ResponseDTO> searchPositionByName(@RequestParam(name="s", defaultValue="all") String search) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "직급 검색 성공",  positionService.searchPositionByName(search)));
    }
	
	
}