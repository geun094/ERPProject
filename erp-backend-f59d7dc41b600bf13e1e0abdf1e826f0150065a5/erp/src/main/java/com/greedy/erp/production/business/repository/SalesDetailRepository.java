package com.greedy.erp.production.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greedy.erp.production.business.entity.SalesDetail;

@Repository
public interface SalesDetailRepository extends JpaRepository<SalesDetail, Integer>{

	SalesDetail findBySalesNo(int salesNo);

}