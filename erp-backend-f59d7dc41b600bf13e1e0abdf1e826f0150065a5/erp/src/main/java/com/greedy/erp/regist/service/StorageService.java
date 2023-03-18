package com.greedy.erp.regist.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.greedy.erp.common.Criteria;
import com.greedy.erp.regist.dto.DeptDTO;
import com.greedy.erp.regist.dto.StorageDTO;
import com.greedy.erp.regist.entity.Dept;
import com.greedy.erp.regist.entity.Storage;
import com.greedy.erp.regist.repository.StorageRepository;

@Service
public class StorageService {
	
	private static final Logger log = LoggerFactory.getLogger(StorageService.class);
	private final StorageRepository storageRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public StorageService(StorageRepository storageRepository, ModelMapper modelMapper) {
		this.storageRepository = storageRepository;
		this.modelMapper = modelMapper;
	}
	
	public int selectStorageTotal() {
		log.info("[StorageService] selectStorageTotal Start ==========");
		List<Storage> storageList = storageRepository.findAll();
		log.info("[StorageService] selectStorageTotal End ==========");
		
		return storageList.size();
	}
	public Object selectStorageListWithPaging(Criteria cri) {
		log.info("[StorageService] selectStorageListWithPaging Start ==========");
		
		int index = cri.getPageNum() - 1;
	    int count = cri.getAmount(); 
	    Pageable paging = PageRequest.of(index, count, Sort.by("storageCode").descending());
	        
	    Page<Storage> result = storageRepository.findAll(paging);
	    List<Storage> storageList = (List<Storage>)result.getContent();
	    
	    log.info("[StorageService] selectStorageListWithPaging End ============");
    
    return storageList.stream().map(storage -> modelMapper.map(storage, StorageDTO.class)).collect(Collectors.toList());
	}
	public Object selectStorage(int storageCode) {
		log.info("[StorageService] selectStorage Start =====");
		
		Storage storage = storageRepository.findById(storageCode).get();
		
		log.info("[StorageService] selectStorage End =======");
		
        StorageDTO storageDTO = modelMapper.map(storage, StorageDTO.class);
        
		return storageDTO;
	}
	
	public Object searchStorageByType(String search) {
		List<Storage> searchByType = storageRepository.findByStorageTypeContaining(search);
		return searchByType.stream().map(storage -> modelMapper.map(storage, StorageDTO.class)).collect(Collectors.toList());
	}
	
	
	@Transactional
	public Object insertStorage(StorageDTO storageDTO) {
		log.info("[StorageService] ===== insertStorage Start =====");
		log.info("[StorageService] storageDTO : " + storageDTO);
        
		int result = 0;
		try {	
			Storage storage = modelMapper.map(storageDTO, Storage.class); // 한번에 전체 등록 매핑
			
			
			storageRepository.save(storage);
			
			result = 1;
		} catch (Exception e) {
			log.info("[storage] exception");
		}
		
		log.info("[StorageService] ====== insertStorage End ======");
		return (result > 0)? "등록 성공" : "등록 실패";
		}
	
	@Transactional
	public Object updateStorage(StorageDTO storageDTO) {
		log.info("[StorageService] ===== updateStorage Start =====");
		
		int result = 0;
		
		try {
			Storage storage = storageRepository.findById(storageDTO.getStorageCode()).get();
			storage.setStorageCode(storageDTO.getStorageCode());
			storage.setStorageName(storageDTO.getStorageName());
			storage.setStorageType(storageDTO.getStorageType());
			
			result = 1;
		} catch (Exception e) {
			log.info("[review update] Exception!!");
		}
		
		log.info("[StorageService] ====== updateStorage End ======");
		return (result > 0) ? "창고 수정 성공" : "창고 수정 실패" ;
	}
		@Transactional
	public Object deleteStorage(int storageCode) {
	    int result = 0;
	    
	    try {
	        storageRepository.deleteById(storageCode);
	        result = 1;
	    } catch (Exception e) {
	    }
	    
	    return (result > 0) ? "창고 삭제 성공" : "창고 삭제 실패";
	};
	@Transactional
	public Object deleteStorages(List<Integer> storageCodes) {
	    storageRepository.deleteAllInBatch(storageRepository.findAllById(storageCodes));
	    return storageCodes;
	}
		

};
	
