package com.greedy.erp.production.purchase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greedy.erp.production.purchase.entity.Place;

@Repository
public interface PlaceRepository  extends JpaRepository<Place, Integer> {

}
