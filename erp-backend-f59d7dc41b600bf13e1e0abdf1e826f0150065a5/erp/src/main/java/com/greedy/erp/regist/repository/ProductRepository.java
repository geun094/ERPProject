package com.greedy.erp.regist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.erp.regist.entity.Product;
import com.greedy.erp.regist.entity.Storage;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	List<Product> findByProductNameContaining(String search);
	
	Product findByProductCode(int productCode);

}
