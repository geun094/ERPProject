package com.greedy.erp.regist.service;

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
import com.greedy.erp.regist.dto.DeptDTO;
import com.greedy.erp.regist.dto.EmpDTO;
import com.greedy.erp.regist.dto.StorageDTO;
import com.greedy.erp.regist.entity.Dept;
import com.greedy.erp.regist.entity.Emp;
import com.greedy.erp.regist.entity.Storage;
import com.greedy.erp.regist.repository.DeptRepository;

@Service
public class DeptService {
	
	private static final Logger log = LoggerFactory.getLogger(DeptService.class);
	private final ModelMapper modelMapper;
	private final DeptRepository deptRepository;
	
	@Autowired
    public DeptService(DeptRepository deptRepository, ModelMapper modelMapper) {
		this.deptRepository = deptRepository;
		this.modelMapper = modelMapper;
	}
	
	/* 전체 부서 조회 */
	public int selectDeptTotal() {
		List<Dept> deptList = deptRepository.findAll();
		return deptList.size();
	}
	public Object selectDeptList(Criteria cri) {
		int index = cri.getPageNum() - 1;
	    int count = cri.getAmount(); 
	    Pageable paging = PageRequest.of(index, count, Sort.by("deptCode").descending());
	    Page<Dept> result = deptRepository.findAll(paging);
	    List<Dept> deptList = (List<Dept>)result.getContent();
	    return deptList.stream().map(dept -> modelMapper.map(dept, DeptDTO.class)).collect(Collectors.toList());
	}
	
	/* 부서 코드로 조회 */
	public Object selectDeptByCode(int deptCode) {
		Dept dept = deptRepository.findById(deptCode).get();
		return modelMapper.map(dept, DeptDTO.class);
	}
	
	/* 신규 부서 등록 */
	@Transactional
	public Object insertNewDept(DeptDTO deptDTO) {
        log.info("[DeptService] deptDTO : " + deptDTO);
		int result = 0;
		try {	
			Dept insertDept = modelMapper.map(deptDTO, Dept.class);
			deptRepository.save(insertDept);
			result = 1;
		} catch (Exception e) {
			log.info("[dept insert] exception!!");
		}
        return  (result > 0) ? "부서 등록 성공" : "부서 등록 실패";
	}

	/* 부서 수정 */
	@Transactional
	public Object updateDept(DeptDTO deptDTO) {
	    int result = 0;
		try {
			Dept newDept = deptRepository.findById(deptDTO.getDeptCode()).get();
			newDept.setDeptName(deptDTO.getDeptName());
			newDept.setDeptSalary(deptDTO.getDeptSalary());
	        result = 1;
		} catch (Exception e) {
			log.info("[dept update] Exception!!");
		}
		return (result > 0) ? "부서 수정 성공" : "부서 수정 실패";
	}

	/* 부서 하나 삭제 */
	@Transactional
	public Object deleteDept(int deptCode) {
	    int result = 0;
	    try {
	    	deptRepository.deleteById(deptCode);
	        result = 1;
	    } catch (Exception e) {
	        log.info("[dept delete] Exception!!");
	    }
	    return (result > 0) ? "부서 삭제 성공" : "부서 삭제 실패";
	}

	/* 부서 코드 조회 */
	public Object selectDeptCodes() {
		List<Integer> deptCodes = deptRepository.selectDeptCodes();
		return deptCodes;
	}

	/* 부서 복수 삭제 */
	@Transactional
	public Object deleteDepts(List<Integer> deptCodes) {
		deptRepository.deleteAllInBatch(deptRepository.findAllById(deptCodes));
		return deptCodes;
	}

	/* 부서명으로 검색 */
	public Object searchDeptByName(String search) {
		List<Dept> searchedDepts = deptRepository.findByDeptNameContaining(search);
		return searchedDepts.stream().map(dept -> modelMapper.map(dept, DeptDTO.class)).collect(Collectors.toList());
	}
	
	// 전체 부서 조회하기
	public List<DeptDTO> selectAllDept() {
		log.info("[DeptService] selectAllDept Start ==========");
		
		List<Dept> deptList = deptRepository.findAll();
		
		log.info("[DeptService] selectAllDept End ==========");
		
		return deptList.stream().map(dept -> modelMapper.map(dept, DeptDTO.class)).collect(Collectors.toList());
		
	}
	
}
