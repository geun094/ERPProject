package com.greedy.erp.production.purchase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greedy.erp.production.purchase.entity.Request;

@Repository
public interface RequestRepository  extends JpaRepository<Request, Integer> {

}
