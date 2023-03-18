package com.greedy.erp.production.production.service;

import java.util.List;
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

import com.greedy.erp.common.Criteria;
import com.greedy.erp.production.business.dto.EstimateDTO;
import com.greedy.erp.production.business.entity.Estimate;
import com.greedy.erp.production.production.dto.ForwardingDTO;
import com.greedy.erp.production.production.dto.WorkDTO;
import com.greedy.erp.production.production.entity.Forwarding;
import com.greedy.erp.production.production.entity.Work;
import com.greedy.erp.production.production.repository.ForwardingRepository;
import com.greedy.erp.production.production.repository.WorkRepository;

@Service
public class ProductionService {
	
	private static final Logger log = LoggerFactory.getLogger(ProductionService.class);
	private final ModelMapper modelMapper;
	private final WorkRepository workRepository;
	private final ForwardingRepository forwardingRepository;
    
	@Autowired
	public ProductionService(ModelMapper modelMapper, WorkRepository workRepository, ForwardingRepository forwardingRepository) {
		this.modelMapper = modelMapper;
		this.workRepository = workRepository;
		this.forwardingRepository = forwardingRepository;
	}
	
/* <=============================== 작업 관련 서비스 =====================================> */
	
	public WorkDTO findWorkByWorkCode(int workCode) {
		
		Work work = workRepository.findById(workCode).get();
		
		return modelMapper.map(work, WorkDTO.class);
	}
	
	/* 전체 작업 조회하기 */
	public Object selectWorkListWithPaging(Criteria cri) {
		log.info("[WorkService] selectWorkListWithPaging Start ==========");
		
		int index = cri.getPageNum() - 1;
	    int count = cri.getAmount(); 
	    Pageable paging = PageRequest.of(index, count, Sort.by("workCode").descending());
	    Page<Work> result = workRepository.findAll(paging);
	    List<Work> workList = (List<Work>)result.getContent();
	    
	    log.info("[WorkService] selectWorkListWithPaging End ============");
    
    return workList.stream().map(work -> modelMapper.map(work, WorkDTO.class)).collect(Collectors.toList());
	}
	
	public int selectEmployeeTotal() {
		log.info("[EmployeeService] selectEmployeeTotal Start ==========");
		List<Work> empList = workRepository.findAll();
		log.info("[EmployeeService] selectEmployeeTotal End ==========");
		
		return empList.size();
	}

//	/* 작업 등록 */
//	@Transactional
//	public Object insertEmployee(EmpDTO empDTO) {
//		log.info("[EmployeeService] ===== insertEmployee Start =====");
//		log.info("[EmployeeService] EmpDTO : " + empDTO);
//        
//		int result = 0;
//		try {	
//			Emp emp = modelMapper.map(empDTO, Emp.class); 
//			
//			employeeRepository.save(emp);
//			
//			result = 1;
//		} catch (Exception e) {
//			log.info("[EmployeeService] exception");
//		}
//		
//		log.info("[EmployeeService] ====== insertEmployee End ======");
//		return (result > 0)? "등록 성공" : "등록 실패";
//		}
//
//	/* 작업 수정 */
//	@Transactional
//	public Object modifyEmployee(EmpDTO empDTO) {
//		log.info("[EmployeeService] ===== modifyEmployee Start =====");
//		log.info("[EmployeeService] EmpDTO : " + empDTO);
//        
//		int result = 0;
//		try {	
//			Emp emp = modelMapper.map(empDTO, Emp.class); 
//			
//			employeeRepository.save(emp);
//			
//			result = 1;
//		} catch (Exception e) {
//			log.info("[EmployeeService] exception");
//		}
//		
//		log.info("[EmployeeService] ====== modifyEmployee End ======");
//		return (result > 0)? "수정 성공" : "수정 실패";
//		}
	
	/* <=============================== 출고 관련 서비스 =====================================> */
	
	/* 출고코드로 출고 1개 조회하기 */
	public ForwardingDTO findForwardingByForwardingCode(Integer forwardingCode) {
		
		Forwarding forwarding = forwardingRepository.findById(forwardingCode).get();
		
		return modelMapper.map(forwarding, ForwardingDTO.class);
	}
	
	/* 전체 출고 조회하기 */
	public Object selectForwardingListWithPaging(Criteria cri) {
		log.info("[ProductionService] selectForwardingListWithPaging Start ==========");
		
		int index = cri.getPageNum() - 1;
	    int count = cri.getAmount(); 
	    Pageable paging = PageRequest.of(index, count, Sort.by("forwardingCode").descending());
	    Page<Forwarding> result = forwardingRepository.findAll(paging);
	    List<Forwarding> forwardingList = (List<Forwarding>)result.getContent();
	    
	    log.info("[ProductionService] selectForwardingListWithPaging End ============");
    
    return forwardingList.stream().map(forwarding -> modelMapper.map(forwarding, ForwardingDTO.class)).collect(Collectors.toList());
	}
	
	public int selectForwardingTotal() {
		log.info("[ProductionService] selectForwardingTotal Start ==========");
		List<Forwarding> forwardingList = forwardingRepository.findAll();
		log.info("[ProductionService] selectForwardingTotal End ==========");
		
		return forwardingList.size();
	}
	
	/* 출고 추가하기 */
	@Transactional
	public Object insertForwarding(ForwardingDTO forwardingDTO) {
		log.info("[ForwardingService] ===== insertForwarding Start =====");
		log.info("[ForwardingService] forwardingDTO : " + forwardingDTO);
		int result = 0;
		try {	
			Forwarding forwarding = modelMapper.map(forwardingDTO, Forwarding.class);
			log.info("포워딩 엔티티에서 찍히는 포워딩 코드 : " + forwarding.getForwardingCode());
			forwardingRepository.save(forwarding);
			result = 1;
		} catch (Exception e) {
			log.info("[forwarding] exception");
		}
		log.info("[ForwardingService] ====== insertForwarding End ======");
		return (result > 0)? "등록 성공" : "등록 실패";
		}

}
