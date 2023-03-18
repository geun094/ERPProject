package com.greedy.erp.regist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greedy.erp.regist.entity.Emp;


@Repository
public interface EmployeeRepository extends JpaRepository<Emp, Integer>{
	
	public Emp findEmpByEmpCode(Integer empCode);

}
