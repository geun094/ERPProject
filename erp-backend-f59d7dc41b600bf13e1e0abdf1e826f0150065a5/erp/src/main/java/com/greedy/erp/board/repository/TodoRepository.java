package com.greedy.erp.board.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.greedy.erp.board.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer> {

	
	/* 업무 조회 */
	
	@Query(value="SELECT * FROM TODO WHERE EMP_CODE = :empCode ORDER BY TODO_CODE DESC", nativeQuery = true)
	List<Todo> findByEmpCode(@Param("empCode")int empCode);
	
	@Query(value="SELECT * FROM TODO WHERE EMP_CODE = :empCode ORDER BY TODO_CODE DESC", nativeQuery = true)
	List<Todo> findByEmpCode(int empCode, Pageable paging);

	/* 검색 */
	List<Todo> findByCompleteYn(String search);

}
