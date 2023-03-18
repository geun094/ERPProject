package com.greedy.erp.regist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.erp.regist.entity.Storage;

public interface StorageRepository extends JpaRepository<Storage, Integer> {

//	List<Storage> findByStorageCode(int storageCode);
	
	List<Storage> findByStorageNameContaining(String search);
	List<Storage> findByStorageTypeContaining(String search);
	List<Storage> findByStorageCodeContaining(String search);
	
	Storage findByStorageCode(int storageCode);

	
}
