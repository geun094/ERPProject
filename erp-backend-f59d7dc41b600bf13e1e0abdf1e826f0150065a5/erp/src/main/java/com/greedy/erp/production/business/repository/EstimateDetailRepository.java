package com.greedy.erp.production.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.greedy.erp.production.business.entity.Estimate;
import com.greedy.erp.production.business.entity.EstimateDetail;

@Repository
public interface EstimateDetailRepository extends JpaRepository<EstimateDetail, Integer>{
	
	EstimateDetail findByEstimateNo(int estimateNo);

}