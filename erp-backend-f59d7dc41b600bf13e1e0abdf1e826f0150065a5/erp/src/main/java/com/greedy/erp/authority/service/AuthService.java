package com.greedy.erp.authority.service;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.greedy.erp.authority.dto.TokenDTO;
import com.greedy.erp.authority.repository.EmpRepository;
import com.greedy.erp.authority.repository.EmpRoleRepository;
import com.greedy.erp.exception.LoginFailedException;
import com.greedy.erp.jwt.TokenProvider;
import com.greedy.erp.regist.dto.EmpDTO;
import com.greedy.erp.regist.entity.Emp;

@Service
public class AuthService {

	private static final Logger log = LoggerFactory.getLogger(AuthService.class);
	private final EmpRepository empRepository;
	private final PasswordEncoder passwordEncoder;
	private final TokenProvider tokenProvider;
	private final ModelMapper modelMapper;
	private final EmpRoleRepository empRoleRepository;
	
	@Autowired
	public AuthService(EmpRepository empRepository
					 , PasswordEncoder passwordEncoder
					 , TokenProvider tokenProvider
					 , ModelMapper modelMapper
					 , EmpRoleRepository empRoleRepository) {
		this.empRepository = empRepository;
		this.passwordEncoder = passwordEncoder;
		this.tokenProvider = tokenProvider;
		this.modelMapper = modelMapper;
		this.empRoleRepository = empRoleRepository;
	}
	
	public Object login(EmpDTO empDTO) {
		
		log.info("[AuthService] 로그인 스따또~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		log.info("[AuthService] {}", empDTO);
		
		
		// 1. 사번 조회
		Emp emp = empRepository.findByEmpCode(empDTO.getEmpCode());
		
		if(emp == null) {
			throw new LoginFailedException("존재하지 않는 사원번호입니다.");
		}
		
		// 2. 비밀번호 매칭
		//    (BCrypt 암호화 라이브러리 bean을 의존성 주입받아 처리하는 부분부터 security 설정 부분을 추가해보자.)
		//  mathces(평문, 다이제스트)
		if(!passwordEncoder.matches(empDTO.getEmpPw(), emp.getEmpPw())){
			log.info("[AuthService] 패스워드 틀려쪄염!!!!");
			throw new LoginFailedException("잘못된 비밀번호입니다.");
		}
		
		
		// 3. 토큰 발급
		TokenDTO tokenDTO = tokenProvider.generateTokenDTO(emp);
		log.info("[AuthService] tokenDTO{}", tokenDTO);
		
		log.info("[AuthService] 로그인 끄으으으으으으으으읕~~~~~~~~~~~~~~~~");
		return tokenDTO;
	}
	
	
	
	
	
	
	
	
	
	
}
