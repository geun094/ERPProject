package com.greedy.erp.production.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.erp.production.stock.entity.Stock;
import com.greedy.erp.regist.entity.Storage;

public interface StockRepository extends JpaRepository<Stock, Integer> {
	
	Stock findByStockCode(int stockCode);


}
