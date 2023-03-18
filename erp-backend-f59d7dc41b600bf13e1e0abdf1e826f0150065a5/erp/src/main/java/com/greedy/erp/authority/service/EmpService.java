package com.greedy.erp.authority.service;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.erp.authority.repository.EmpRepository;
import com.greedy.erp.regist.dto.EmpDTO;
import com.greedy.erp.regist.entity.Emp;

@Service
public class EmpService {
	
	private static final Logger log= LoggerFactory.getLogger(EmpService.class);
	private final EmpRepository empRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public EmpService(EmpRepository empRepository
					, ModelMapper modelMapper) {
		this.empRepository = empRepository;
		this.modelMapper = modelMapper;
	}

	public EmpDTO selectMyInfo(String empCode) {
		
		log.info("[MemberService] getMyInfo Start =======================");
		
		Emp emp = empRepository.findByEmpCode(Integer.valueOf(empCode));
		log.info("[MemberService] {}", emp);
		log.info("[MemberService] getMyInfo End =========================");
		
		return modelMapper.map(emp, EmpDTO.class);

		
		
	}
	
	
	
	
}
