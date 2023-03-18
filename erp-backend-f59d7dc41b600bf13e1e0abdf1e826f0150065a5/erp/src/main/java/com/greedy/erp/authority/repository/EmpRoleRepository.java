package com.greedy.erp.authority.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.erp.authority.entity.Authority;
import com.greedy.erp.authority.entity.EmpRolePk;

public interface EmpRoleRepository extends JpaRepository<Authority, EmpRolePk>{

}
