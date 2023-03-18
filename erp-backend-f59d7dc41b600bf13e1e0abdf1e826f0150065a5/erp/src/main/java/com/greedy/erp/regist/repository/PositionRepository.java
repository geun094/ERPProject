package com.greedy.erp.regist.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.erp.regist.entity.Position;

public interface PositionRepository extends JpaRepository<Position, Integer> {

	
	List<Position> findByPositionName(String search);
	
	List<Position> findByPositionCode(int positionCode);
	
	List<Position> findByPositionSalary(int positionSalary);
	

}
