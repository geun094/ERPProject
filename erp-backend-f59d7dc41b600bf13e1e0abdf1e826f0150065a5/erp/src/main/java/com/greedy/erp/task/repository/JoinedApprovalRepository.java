package com.greedy.erp.task.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.greedy.erp.task.entity.JoinedApproval;

public interface JoinedApprovalRepository extends JpaRepository<JoinedApproval, Integer>{

	@Query(value= "SELECT * FROM APPROVAL WHERE STATUS_CODE NOT IN (4, 9) ORDER BY APPROVAL_DATE DESC", nativeQuery = true)
	List<JoinedApproval> findValid(Pageable paging);

	@Query(value= "SELECT * FROM APPROVAL WHERE STATUS_CODE NOT IN (4, 9) ORDER BY APPROVAL_DATE DESC", nativeQuery = true)
	List<JoinedApproval> findValid();

	@Query(value= "SELECT * FROM APPROVAL WHERE STATUS_CODE NOT IN (4, 9) AND APPROVAL_TITLE LIKE(:likeSearch) ORDER BY APPROVAL_DATE DESC", nativeQuery = true)
	List<JoinedApproval> findValidByApprovalTitleContaining(@Param("likeSearch") String likeSearch);

	@Query(value= "SELECT * FROM APPROVAL WHERE STATUS_CODE NOT IN (4, 9) AND EMP_CODE = :empCode ORDER BY APPROVAL_DATE DESC", nativeQuery = true)
	List<JoinedApproval> findValidByEmpCode(@Param("empCode") int empCode);

	@Query(value= "SELECT * FROM APPROVAL WHERE STATUS_CODE NOT IN (4, 9) AND EMP_CODE = :empCode ORDER BY APPROVAL_DATE DESC", nativeQuery = true)
	List<JoinedApproval> findValidByEmpCode(Pageable paging, @Param("empCode") int empCode);

	@Query(value= "SELECT * FROM APPROVAL WHERE STATUS_CODE IN (4) AND EMP_CODE = :empCode ORDER BY APPROVAL_DATE DESC", nativeQuery = true)
	List<JoinedApproval> findDraftByEmpCode(@Param("empCode") int empCode);

	@Query(value= "SELECT * FROM APPROVAL WHERE STATUS_CODE IN (4) AND EMP_CODE = :empCode ORDER BY APPROVAL_DATE DESC", nativeQuery = true)
	List<JoinedApproval> findDraftByEmpCode(Pageable paging, @Param("empCode") int empCode);

	List<JoinedApproval> findByApprovalCodeIn(int[] arr);

	List<JoinedApproval> findByApprovalCodeIn(Pageable paging, int[] arr);

	@Query(value= "SELECT * FROM APPROVAL WHERE STATUS_CODE NOT IN (4, 5, 9) AND APPROVAL_CODE IN (:approvalCodes) ORDER BY APPROVAL_DATE DESC", nativeQuery = true)
	List<JoinedApproval> findValidByApprovalCodeIn(@Param("approvalCodes") int[] codeArr);

	@Query(value= "SELECT * FROM APPROVAL WHERE STATUS_CODE NOT IN (4, 5, 9) AND APPROVAL_CODE IN (:approvalCodes) ORDER BY APPROVAL_DATE DESC", nativeQuery = true)
	List<JoinedApproval> findDoneByApprovalCodeIn(@Param("approvalCodes") int[] arr, Pageable paging);

	

}
