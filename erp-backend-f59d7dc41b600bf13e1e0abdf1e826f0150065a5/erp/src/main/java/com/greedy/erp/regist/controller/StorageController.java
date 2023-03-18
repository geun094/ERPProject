package com.greedy.erp.regist.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.greedy.erp.common.Criteria;
import com.greedy.erp.common.PageDTO;
import com.greedy.erp.common.PagingResponseDTO;
import com.greedy.erp.common.ResponseDTO;
import com.greedy.erp.regist.dto.DeptDTO;
import com.greedy.erp.regist.dto.StorageDTO;
import com.greedy.erp.regist.entity.Storage;
import com.greedy.erp.regist.service.StorageService;

import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("/api/v1")
public class StorageController {	
	private static final Logger log = LoggerFactory.getLogger(StorageController.class);
	private final StorageService storageService;

	@Autowired
	public StorageController(StorageService storageService) {
		this.storageService = storageService;	
	}
		
	@Operation(summary = "창고 조회", description = "창고 목록 조회", tags = { "StorageController" })
	@GetMapping("/storages")
	public ResponseEntity<ResponseDTO> selectStorage(@RequestParam(name = "offset", defaultValue = "1") String offset) {
		log.info("[storageController] selectStorage : " + offset);	
		int total = storageService.selectStorageTotal();
		Criteria cri = new Criteria(Integer.valueOf(offset), 10);
		PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
		pagingResponseDTO.setData(storageService.selectStorageListWithPaging(cri));	
		pagingResponseDTO.setPageInfo(new PageDTO(cri, total));	
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "창고 조회 성공", pagingResponseDTO));	
	}

	@Operation(summary = "창고 상세 조회", description = "창고 상세 조회 진행", tags = { "StorageController" })
    @GetMapping("/storages/{storageCode}")
    public ResponseEntity<ResponseDTO> detailStorage(@PathVariable int storageCode) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "창고 상세 조회 성공",  storageService.selectStorage(storageCode)));
    }
	
	@Operation(summary = "창고 검색", description = "창고 검색 진행", tags = { "StorageController" })
	@GetMapping("/storages/search")
    public ResponseEntity<ResponseDTO> searchStorageByType(@RequestParam(name="s", defaultValue="all") String search) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "창고 검색 성공",  storageService.searchStorageByType(search)));
    }
		
	@Operation(summary = "창고 등록", description = "창고 등록 진행", tags = { "StorageController" })
    @PostMapping(value = "/storages")
    public ResponseEntity<ResponseDTO> insertStorage(@ModelAttribute StorageDTO storageDTO) {
    	return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "창고 등록 성공",  storageService.insertStorage(storageDTO)));
    }
	
	@Operation(summary = "창고 수정", description = "창고 수정 진행", tags = { "StorageController" })
    @PutMapping("/storages")
    public ResponseEntity<ResponseDTO> updateStorage(@ModelAttribute StorageDTO storageDTO) {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "창고 수정 성공", storageService.updateStorage(storageDTO)));
    }
	
	@Operation(summary = "창고 삭제", description = "창고 삭제 진행", tags = { "StorageController" })
	@DeleteMapping("/storages/{storageCode}")
	public ResponseEntity<ResponseDTO> deleteStorage(@PathVariable Integer storageCode) {
	    return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "창고 삭제 성공", storageService.deleteStorage(storageCode)));
	}
	
	@Operation(summary = "창고 목록 삭제", description = "창고 목록 삭제 진행", tags = { "StorageController" })
	@DeleteMapping("/storages")
    public ResponseEntity<ResponseDTO> deleteStorages(@RequestBody List<Integer> storageCodes) {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "창고 목록 삭제 성공", storageService.deleteStorages(storageCodes)));
    }
	
	
}
