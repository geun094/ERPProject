package com.greedy.erp.task.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.greedy.erp.task.entity.ApprovalLine;


public interface ApprovalLineRepository extends JpaRepository<ApprovalLine, Integer>{

	List<ApprovalLine> findByApprovalCodeOrderByApproverOrderAsc(int approvalCode);

	void deleteByApprovalCode(int approvalCode);

	@Query(value= "SELECT * FROM APPROVAL_LINE WHERE EMP_CODE = :empCode ORDER BY APPROVAL_CODE DESC", nativeQuery = true)
	List<ApprovalLine> findProcessByEmpCode(@Param("empCode")int empCode);
	
	@Query(value= "SELECT * FROM APPROVAL_LINE WHERE EMP_CODE = :empCode AND APPROVE_YN = 'Y' ORDER BY APPROVAL_CODE DESC", nativeQuery = true)
	List<ApprovalLine> findDoneByEmpCode(@Param("empCode")int empCode);

	@Query(value= "SELECT * FROM APPROVAL_LINE WHERE EMP_CODE = :empCode AND APPROVAL_CODE = :approvalCode ORDER BY APPROVAL_CODE DESC", nativeQuery = true)
	ApprovalLine updateApproveYn(@Param("empCode")int empCode, @Param("approvalCode")int approvalCode);

	@Query(value= "SELECT APPROVE_YN FROM APPROVAL_LINE WHERE APPROVAL_CODE = :approvalCode AND APPROVER_ORDER = :approverOrder", nativeQuery = true)
	String findApprovedYn(@Param("approvalCode") int approvalCode, @Param("approverOrder") int approverOrder);


}
