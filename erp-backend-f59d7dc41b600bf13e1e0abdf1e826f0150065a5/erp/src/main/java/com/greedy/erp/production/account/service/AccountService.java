package com.greedy.erp.production.account.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.greedy.erp.authority.repository.EmpRepository;
import com.greedy.erp.common.Criteria;
import com.greedy.erp.production.account.dto.DepositDTO;
import com.greedy.erp.production.account.dto.WithdrawDTO;
import com.greedy.erp.production.account.entity.Deposit;
import com.greedy.erp.production.account.entity.Withdraw;
import com.greedy.erp.production.account.repository.DepositRepository;
import com.greedy.erp.production.account.repository.WithdrawRepository;
import com.greedy.erp.production.business.dto.OrdersDTO;
import com.greedy.erp.production.business.dto.SalesDTO;
import com.greedy.erp.production.business.dto.SalesDetailDTO;
import com.greedy.erp.production.business.entity.Orders;
import com.greedy.erp.production.business.entity.Sales;
import com.greedy.erp.production.business.entity.SalesDetail;
import com.greedy.erp.production.business.repository.OrdersRepository;
import com.greedy.erp.production.business.repository.SalesDetailRepository;
import com.greedy.erp.production.business.repository.SalesRepository;
import com.greedy.erp.production.purchase.repository.PurchaseDetailRepository;
import com.greedy.erp.production.purchase.repository.PurchaseRepository;
import com.greedy.erp.production.stock.entity.Stock;
import com.greedy.erp.production.stock.repository.StockRepository;
import com.greedy.erp.regist.repository.ClientRepository;
import com.greedy.erp.regist.repository.ProductRepository;
import com.greedy.erp.regist.repository.StorageRepository;

@Service
public class AccountService {
	
	private static final Logger log = LoggerFactory.getLogger(AccountService.class);
	private final DepositRepository depositRepository;
	private final WithdrawRepository withdrawRepository;
	
	private final SalesRepository salesRepository;
	private final PurchaseRepository purchaseRepository;

	private final SalesDetailRepository salesDetailRepository;
	private final PurchaseDetailRepository purchaseDetailRepository;
	
	
	private final ClientRepository clientRepository;
	private final EmpRepository empRepository;
	private final StorageRepository storageRepository;
	private final ProductRepository productRepository;
	private final StockRepository stockRepository;
	
	private final ModelMapper modelMapper;
	
	@Autowired
	public AccountService(
			DepositRepository depositRepository,
			WithdrawRepository withdrawRepository,
			
			SalesRepository salesRepository,
			PurchaseRepository purchaseRepository,

			
			SalesDetailRepository salesDetailRepository,
			PurchaseDetailRepository purchaseDetailRepository,
			
			ClientRepository clientRepository,
			EmpRepository empRepository,
			StorageRepository storageRepository,
			ProductRepository productRepository,
			StockRepository stockRepository,
			
			ModelMapper modelMapper) {
		
		this.depositRepository = depositRepository;
		this.withdrawRepository = withdrawRepository;
		
		this.salesRepository = salesRepository;
		this.purchaseRepository = purchaseRepository;
		
		this.salesDetailRepository = salesDetailRepository;
		this.purchaseDetailRepository = purchaseDetailRepository;
		
		this.clientRepository = clientRepository;
		this.empRepository = empRepository;
		this.storageRepository = storageRepository;
		this.productRepository = productRepository;
		this.stockRepository = stockRepository;
		
		this.modelMapper = modelMapper;
	}
	
	public int selectDepositTotal() {
		log.info("[DepositService] selectDepositTotal Start ==========");
		List<Deposit> depositList = depositRepository.findAll();
		log.info("[DepositService] selectDepositTotal End ==========");
		return depositList.size();
	}
	
	public Object selectDepositListWithPaging(Criteria cri) {
		log.info("[DepositService] selectDepositListWithPaging Start ==========");
		int index = cri.getPageNum() - 1;
	    int count = cri.getAmount(); 
	    Pageable paging = PageRequest.of(index, count, Sort.by("depositCode").descending());
	    Page<Deposit> result = depositRepository.findAll(paging);
	    List<Deposit> depositList = (List<Deposit>)result.getContent();
	    log.info("[DepositService] selectDepositListWithPaging End ============");
    return depositList.stream().map(deposit -> modelMapper.map(deposit, DepositDTO.class)).collect(Collectors.toList());
	}
	
	public Object selectDeposit(int depositCode) {
		log.info("[DepositService] selectDeposit Start =====");
		Deposit deposit = depositRepository.findById(depositCode).get();
		log.info("[DepositService] selectDeposit End =======");
        DepositDTO depositDTO = modelMapper.map(deposit, DepositDTO.class);
		return depositDTO;
	}
	
	@Transactional
	public Object insertDeposit(DepositDTO depositDTO) {
		log.info("[DepositService] ===== insertDeposit Start =====");
		log.info("[DepositService] depositDTO : " + depositDTO);
		int result = 0;
		try {	
			Deposit deposit = modelMapper.map(depositDTO, Deposit.class);
			depositRepository.save(deposit);
			result = 1;
		} catch (Exception e) {
			log.info("[deposit] exception");
		}
		log.info("[DepositService] ====== insertDeposit End ======");
		return (result > 0)? "등록 성공" : "등록 실패";
		}
	
	@Transactional
	public Object updateDeposit(DepositDTO depositDTO) {
		log.info("[DepositService] ===== updateDeposit Start =====");
		int result = 0;
		try {
			Deposit deposit = depositRepository.findById(depositDTO.getDepositCode()).get();
			deposit.setDepositCode(depositDTO.getDepositCode());
			deposit.setDepositDate(depositDTO.getDepositDate());
			deposit.setDepositAmount(depositDTO.getDepositAmount());
			deposit.setDepositReceivable(depositDTO.getDepositReceivable());
			deposit.setDepositNote(depositDTO.getDepositNote());
			deposit.setSales(salesRepository.findBySalesCode(depositDTO.getSales().getSalesCode()));
			
			result = 1;
		} catch (Exception e) {
			log.info("[review update] Exception!!");
		}
		log.info("[DepositService] ====== updateDeposit End ======");
		return (result > 0) ? "지출 수정 성공" : "지출 수정 실패" ;
	}
	
	
	/* 입금 */
	
	public int selectWithdrawTotal() {
		log.info("[WithdrawService] selectWithdrawTotal Start ==========");
		List<Withdraw> withdrawList = withdrawRepository.findAll();
		log.info("[WithdrawService] selectWithdrawTotal End ==========");
		return withdrawList.size();
	}
	
	public Object selectWithdrawListWithPaging(Criteria cri) {
		log.info("[WithdrawService] selectWithdrawListWithPaging Start ==========");
		int index = cri.getPageNum() - 1;
	    int count = cri.getAmount(); 
	    Pageable paging = PageRequest.of(index, count, Sort.by("withdrawCode").descending());
	    Page<Withdraw> result = withdrawRepository.findAll(paging);
	    List<Withdraw> withdrawList = (List<Withdraw>)result.getContent();
	    log.info("[WithdrawService] selectWithdrawListWithPaging End ============");
    return withdrawList.stream().map(withdraw -> modelMapper.map(withdraw, WithdrawDTO.class)).collect(Collectors.toList());
	}
	
	public Object selectWithdraw(int withdrawCode) {
		log.info("[WithdrawService] selectWithdraw Start =====");
		Withdraw withdraw = withdrawRepository.findById(withdrawCode).get();
		log.info("[WithdrawService] selectWithdraw End =======");
        WithdrawDTO withdrawDTO = modelMapper.map(withdraw, WithdrawDTO.class);
		return withdrawDTO;
	}
	
	@Transactional
	public Object insertWithdraw(WithdrawDTO withdrawDTO) {
		log.info("[WithdrawService] ===== insertWithdraw Start =====");
		log.info("[WithdrawService] withdrawDTO : " + withdrawDTO);
		int result = 0;
		try {	
			Withdraw withdraw = modelMapper.map(withdrawDTO, Withdraw.class);
			withdrawRepository.save(withdraw);
			result = 1;
		} catch (Exception e) {
			log.info("[withdraw] exception");
		}
		log.info("[WithdrawService] ====== insertWithdraw End ======");
		return (result > 0)? "등록 성공" : "등록 실패";
		}
	
	@Transactional
	public Object updateWithdraw(WithdrawDTO withdrawDTO) {
		log.info("[WithdrawService] ===== updateWithdraw Start =====");
		int result = 0;
		try {
			Withdraw withdraw = withdrawRepository.findById(withdrawDTO.getWithdrawCode()).get();
			withdraw.setWithdrawCode(withdrawDTO.getWithdrawCode());
			withdraw.setWithdrawDate(withdrawDTO.getWithdrawDate());
			withdraw.setWithdrawAmount(withdrawDTO.getWithdrawAmount());
			withdraw.setWithdrawPayable(withdrawDTO.getWithdrawPayable());
			withdraw.setWithdrawNote(withdrawDTO.getWithdrawNote());
			withdraw.setPurchase(purchaseRepository.findByPurchaseCode(withdrawDTO.getPurchase().getPurchaseCode()));
			
			result = 1;
		} catch (Exception e) {
			log.info("[review update] Exception!!");
		}
		log.info("[WithdrawService] ====== updateWithdraw End ======");
		return (result > 0) ? "지출 수정 성공" : "지출 수정 실패" ;
	}
	
}
