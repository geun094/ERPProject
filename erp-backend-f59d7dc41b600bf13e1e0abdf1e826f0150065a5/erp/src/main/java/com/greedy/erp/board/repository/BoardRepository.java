package com.greedy.erp.board.repository;


import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.greedy.erp.board.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>  {

	/*내 게시판 조회*/
	@Query(value="SELECT * FROM BOARD WHERE EMP_CODE = :empCode ORDER BY BOARD_CODE DESC", nativeQuery = true)
	List<Board> findByEmpCode(@Param("empCode") int empCode);

	@Query(value="SELECT * FROM BOARD WHERE EMP_CODE = :empCode ORDER BY BOARD_CODE DESC", nativeQuery = true)
	List<Board> findByEmpCode(int empCode, Pageable paging);

	/*검색*/
	List<Board> findByBoardTitle(String search);

}
