package com.greedy.erp.production.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greedy.erp.production.business.entity.OrdersDetail;

@Repository
public interface OrdersDetailRepository extends JpaRepository<OrdersDetail, Integer>{
	
	OrdersDetail findByOrdersNo(int estimateNo);

}