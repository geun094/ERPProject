package com.greedy.erp.production.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greedy.erp.production.account.entity.Withdraw;

@Repository
public interface WithdrawRepository extends JpaRepository<Withdraw, Integer>{

}