package com.greedy.erp.production.production.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greedy.erp.production.production.entity.Work;


@Repository
public interface WorkRepository extends JpaRepository<Work, Integer>{
	

}
