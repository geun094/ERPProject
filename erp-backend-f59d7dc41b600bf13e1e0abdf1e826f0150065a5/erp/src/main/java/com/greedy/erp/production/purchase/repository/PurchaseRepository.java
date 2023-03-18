package com.greedy.erp.production.purchase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greedy.erp.production.purchase.entity.Purchase;

@Repository
public interface PurchaseRepository  extends JpaRepository<Purchase, Integer> {
	
	Purchase findByPurchaseCode(int purchaseCode);

}
