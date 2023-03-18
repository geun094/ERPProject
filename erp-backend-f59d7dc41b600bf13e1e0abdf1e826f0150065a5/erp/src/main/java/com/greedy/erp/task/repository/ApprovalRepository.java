package com.greedy.erp.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.greedy.erp.task.entity.Approval;

public interface ApprovalRepository extends JpaRepository<Approval, Integer>{

	@Query(value= "SELECT MAX(APPROVAL_CODE) FROM APPROVAL", nativeQuery = true)
	public int findMaxApproval();

	
}
