package com.greedy.erp.production.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greedy.erp.production.account.entity.Deposit;

@Repository
public interface DepositRepository extends JpaRepository<Deposit, Integer>{

}