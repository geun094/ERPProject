package com.greedy.erp.production.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greedy.erp.production.business.entity.Estimate;

@Repository
public interface EstimateRepository extends JpaRepository<Estimate, Integer>{
	
}