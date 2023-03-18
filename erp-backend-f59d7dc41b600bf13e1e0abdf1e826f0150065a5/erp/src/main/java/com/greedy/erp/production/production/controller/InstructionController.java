package com.greedy.erp.production.production.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
import com.greedy.erp.production.production.dto.InstructionDTO;
import com.greedy.erp.production.production.service.InstructionService;
import com.greedy.erp.regist.controller.ProductController;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("api/v1")
public class InstructionController {

	private static final Logger log = LoggerFactory.getLogger(ProductController.class);

	private final InstructionService instructionService;

	@Autowired
	public InstructionController(InstructionService instructionService) {
		this.instructionService = instructionService;
	}
	
	
	/* 지시서 전체 조회 */
	
	@Operation(summary = "지시서 조회", description = "지시서 목록 조회", tags = { "InstructionController" })
	@GetMapping("/instructions")
	public ResponseEntity<ResponseDTO> selectInstruction(@RequestParam(name = "offset", defaultValue = "1") String offset) {
		log.info("[instructionController] selectInstruction : " + offset);
		
		int total = instructionService.selectInstructionTotal();
		
		Criteria cri = new Criteria(Integer.valueOf(offset), 10);
		PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
		pagingResponseDTO.setData(instructionService.selectInstructionListWithPaging(cri));	
		pagingResponseDTO.setPageInfo(new PageDTO(cri, total));
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "지시서 조회 성공", pagingResponseDTO));	
	}
	
	/* 지시서 등록 */
	@Operation(summary = "지시서 등록", description = "지시서 등록 진행", tags = { "InstructionController" })
    @PostMapping(value = "/instructions")
    public ResponseEntity<ResponseDTO> insertinstruction(@RequestBody InstructionDTO instructionDTO) {
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "지시서 등록 성공",  instructionService.insertInstruction(instructionDTO)));
    }
	
	/* 지시 상세 조회  */
	@Operation(summary = "지시 상세 조회", description = "지시 상세 조회 진행", tags = { "instructionController" })
    @GetMapping("/instructions/{instructionCode}")
    public ResponseEntity<ResponseDTO> detailinstruction(@PathVariable int instructionCode) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "지시서 상세 조회 성공",  instructionService.selectInstruction(instructionCode)));
    }
	
	/* 지시 수정 */
	@Operation(summary = "지시 수정", description = "지시 수정 진행", tags = { "InstructionController" })
    @PutMapping("/instructions")
    public ResponseEntity<ResponseDTO> updateInstruction(@RequestBody InstructionDTO instructionDTO) {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "지시서 수정 성공", instructionService.updateInstruction(instructionDTO)));
    }
	
}
