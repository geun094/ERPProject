package com.greedy.erp.authority.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.greedy.erp.authority.entity.Authority;
import com.greedy.erp.authority.repository.EmpRepository;
import com.greedy.erp.regist.dto.EmpDTO;
import com.greedy.erp.regist.entity.Emp;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	private final EmpRepository empRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public CustomUserDetailsService(EmpRepository empRepository
								  , ModelMapper modelMapper) {
		this.empRepository = empRepository;
		this.modelMapper = modelMapper;
	}

	@Transactional
	@Override
	public UserDetails loadUserByUsername(String empCode) throws UsernameNotFoundException {
		Emp emp = empRepository.findByEmpCode(Integer.valueOf(empCode));
		
		/* MemberDTO는 엔티티를 옮겨 담는 DTO이자 UserDetails이다. */ 
		EmpDTO empDTO = modelMapper.map(emp, EmpDTO.class);
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		for(Authority empRole : emp.getEmpRole()) {
			String authorityName = empRole.getAuthority().getAuthName();
			authorities.add(new SimpleGrantedAuthority(authorityName));
		}
		
		empDTO.setAuthorities(authorities);
		
		return empDTO;
	}
	
	
	
}
