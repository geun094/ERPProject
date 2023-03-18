package com.greedy.erp.production.purchase.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.greedy.erp.production.purchase.entity.Request;
import com.greedy.erp.production.purchase.entity.RequestDetail;

@Repository
public interface RequestDetailRepository extends JpaRepository<RequestDetail, Integer>{
	
	RequestDetail findByRequestNo(int requestNo);

}