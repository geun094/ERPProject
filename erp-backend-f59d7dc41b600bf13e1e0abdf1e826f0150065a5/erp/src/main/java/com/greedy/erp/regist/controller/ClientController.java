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
import com.greedy.erp.regist.dto.ClientDTO;
import com.greedy.erp.regist.service.ClientService;

import io.swagger.v3.oas.annotations.Operation;
;

@RestController
@RequestMapping("api/v1")
public class ClientController {

private static final Logger log = LoggerFactory.getLogger(ClientController.class);
	
	private final ClientService clientService;

	@Autowired
	public ClientController(ClientService clientService) {
		this.clientService = clientService;	
	}
	
	@Operation(summary = "거래처 조회", description = "거래처 목록 조회", tags = { "ClientController" })
	@GetMapping("/clients")
	public ResponseEntity<ResponseDTO> selectClient(@RequestParam(name = "offset", defaultValue = "1") String offset) {
		log.info("[clientController] selectClient : " + offset);
		
		int total = clientService.selectClientTotal();
		
		Criteria cri = new Criteria(Integer.valueOf(offset), 10);
		PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
		pagingResponseDTO.setData(clientService.selectClientListWithPaging(cri));	
		pagingResponseDTO.setPageInfo(new PageDTO(cri, total));
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "거래처 조회 성공", pagingResponseDTO));	
	}
	
	@Operation(summary = "거래처 상세 조회", description = "거래처 상세 조회 진행", tags = { "ClientController" })
    @GetMapping("/client/{clientCode}")
    public ResponseEntity<ResponseDTO> detailClient(@PathVariable int clientCode) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "거래처 상세 조회 성공",  clientService.selectClient(clientCode)));
    }
	
	@Operation(summary = "거래처 검색", description = "거래처 검색 진행", tags = { "ClientController" })
	@GetMapping("/clients/search")
    public ResponseEntity<ResponseDTO> searchClientByType(@RequestParam(name="s", defaultValue="all") String search) {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "거래처 검색 성공",  clientService.searchClientByType(search)));
    }

	@Operation(summary = "거래처 등록", description = "거래처 등록 진행", tags = { "ClientController" })
    @PostMapping(value = "/clients")
    public ResponseEntity<ResponseDTO> insertClient(@ModelAttribute ClientDTO clientDTO) {
    	return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "거래처 등록 성공",  clientService.insertClient(clientDTO)));
    }
	
	@Operation(summary = "거래처 수정", description = "거래처 수정 진행", tags = { "ClientController" })
    @PutMapping("/clients")
    public ResponseEntity<ResponseDTO> updateClient(@ModelAttribute ClientDTO clientDTO) {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "거래처 수정 성공", clientService.updateClient(clientDTO)));
    }
	
	
	@Operation(summary = "거래처 목록 삭제", description = "거래처 목록 삭제 진행", tags = { "ClienteController" })
	@DeleteMapping("/clients")
    public ResponseEntity<ResponseDTO> deleteClients(@RequestBody List<Integer> clientCodes) {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "복수거래처 삭제 성공", clientService.deleteClients(clientCodes)));
    }
}
