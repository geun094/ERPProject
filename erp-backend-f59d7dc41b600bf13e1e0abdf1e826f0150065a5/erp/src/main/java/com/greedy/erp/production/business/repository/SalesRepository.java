package com.greedy.erp.production.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greedy.erp.production.business.entity.EstimateDetail;
import com.greedy.erp.production.business.entity.Sales;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Integer>{
	
	Sales findBySalesCode(int salesCode);

}