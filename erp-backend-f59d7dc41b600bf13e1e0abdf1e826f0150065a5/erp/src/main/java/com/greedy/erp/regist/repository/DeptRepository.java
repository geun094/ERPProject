package com.greedy.erp.regist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.greedy.erp.regist.entity.Dept;

public interface DeptRepository extends JpaRepository<Dept, Integer>{

	@Query(value = "SELECT DEPT_CODE FROM DEPT", nativeQuery = true)
	List<Integer> selectDeptCodes();

	List<Dept> findByDeptNameContaining(String search);

}
