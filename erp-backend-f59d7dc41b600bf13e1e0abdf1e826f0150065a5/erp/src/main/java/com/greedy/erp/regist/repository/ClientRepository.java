package com.greedy.erp.regist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greedy.erp.regist.entity.Client;


@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>{

	Client findByClientCode(Integer clientCode);

	

	public List<Client> findByClientType(String search);



	public List<Client> findAll();



	
	



	
}
