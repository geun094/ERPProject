package com.greedy.erp.production.purchase.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greedy.erp.production.purchase.entity.PlaceDetail;

@Repository
public interface PlaceDetailRepository extends JpaRepository<PlaceDetail, Integer>{
	
	PlaceDetail findByPlaceNo(int requestNo);

}