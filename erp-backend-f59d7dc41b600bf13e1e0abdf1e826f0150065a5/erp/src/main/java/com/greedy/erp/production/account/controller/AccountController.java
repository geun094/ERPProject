package com.greedy.erp.production.account.controller;

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
import com.greedy.erp.production.account.dto.DepositDTO;
import com.greedy.erp.production.account.dto.WithdrawDTO;
import com.greedy.erp.production.account.service.AccountService;

import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("/api/v1")
public class AccountController {
	
	private static final Logger log = LoggerFactory.getLogger(AccountController.class);
	
	private final AccountService accountService;

	@Autowired
	public AccountController(AccountService AccountService) {
		this.accountService = AccountService;	
	}
		
	/* 입금 */
	@Operation(summary = "입금 조회", description = "입금 목록 조회", tags = { "DepositController" })
	@GetMapping("/deposits")
	public ResponseEntity<ResponseDTO> selectDeposit(@RequestParam(name = "offset", defaultValue = "1") String offset) {
		log.info("[depositController] selectDeposit : " + offset);
		
		int total = accountService.selectDepositTotal();
		
		Criteria cri = new Criteria(Integer.valueOf(offset), 10);
		PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
		pagingResponseDTO.setData(accountService.selectDepositListWithPaging(cri));	
		pagingResponseDTO.setPageInfo(new PageDTO(cri, total));
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "입금 조회 성공", pagingResponseDTO));	
	}

	@Operation(summary = "입금 상세 조회", description = "입금 상세 조회 진행", tags = { "DepositController" })
    @GetMapping("/deposit/{depositCode}")
    public ResponseEntity<ResponseDTO> detailDeposit(@PathVariable int depositCode) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "입금 상세 조회 성공",  accountService.selectDeposit(depositCode)));
    }
	
	@Operation(summary = "입금 등록", description = "입금 등록 진행", tags = { "DepositController" })
    @PostMapping(value = "/deposits")
    public ResponseEntity<ResponseDTO> insertDeposit(@RequestBody DepositDTO depositDTO) {
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "입금 등록 성공",  accountService.insertDeposit(depositDTO)));
    }
	
	@Operation(summary = "입금 수정", description = "입금 수정 진행", tags = { "DepositController" })
    @PutMapping("/deposits")
    public ResponseEntity<ResponseDTO> updateDeposit(@RequestBody DepositDTO depositDTO) {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "입금 수정 성공", accountService.updateDeposit(depositDTO)));
    }
	
	
	/* 지출 */
	@Operation(summary = "지출 조회", description = "지출 목록 조회", tags = { "WithdrawController" })
	@GetMapping("/withdraws")
	public ResponseEntity<ResponseDTO> selectWithdraw(@RequestParam(name = "offset", defaultValue = "1") String offset) {
		log.info("[withdrawController] selectWithdraw : " + offset);
		
		int total = accountService.selectWithdrawTotal();
		
		Criteria cri = new Criteria(Integer.valueOf(offset), 10);
		PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
		pagingResponseDTO.setData(accountService.selectWithdrawListWithPaging(cri));	
		pagingResponseDTO.setPageInfo(new PageDTO(cri, total));
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "지출 조회 성공", pagingResponseDTO));	
	}

	@Operation(summary = "지출 상세 조회", description = "지출 상세 조회 진행", tags = { "WithdrawController" })
    @GetMapping("/withdraw/{withdrawCode}")
    public ResponseEntity<ResponseDTO> detailWithdraw(@PathVariable int withdrawCode) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "지출 상세 조회 성공",  accountService.selectWithdraw(withdrawCode)));
    }
	
	@Operation(summary = "지출 등록", description = "지출 등록 진행", tags = { "WithdrawController" })
    @PostMapping(value = "/withdraws")
    public ResponseEntity<ResponseDTO> insertWithdraw(@RequestBody WithdrawDTO withdrawDTO) {
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "지출 등록 성공",  accountService.insertWithdraw(withdrawDTO)));
    }
	
	@Operation(summary = "지출 수정", description = "지출 수정 진행", tags = { "WithdrawController" })
    @PutMapping("/withdraws")
    public ResponseEntity<ResponseDTO> updateWithdraw(@RequestBody WithdrawDTO withdrawDTO) {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "지출 수정 성공", accountService.updateWithdraw(withdrawDTO)));
    }
	
	
	
}
