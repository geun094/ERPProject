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
import org.springframework.stereotype.Service;

import com.greedy.erp.common.Criteria;
import com.greedy.erp.regist.dto.DeptDTO;
import com.greedy.erp.regist.dto.PositionDTO;
import com.greedy.erp.regist.dto.StorageDTO;
import com.greedy.erp.regist.entity.Dept;
import com.greedy.erp.regist.entity.Position;
import com.greedy.erp.regist.entity.Storage;
import com.greedy.erp.regist.repository.PositionRepository;

@Service
public class PositionService {
	
	
	private static final Logger log = LoggerFactory.getLogger(PositionService.class);
	private final PositionRepository positionRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public PositionService( PositionRepository positionRepository, ModelMapper modelMapper) {
		this.positionRepository = positionRepository;
		this.modelMapper = modelMapper;
	}
	
	/* 직급 조회 */
	public int selectPositionTotal() {
		log.info("[PositionService] selectStorageTotal Start ==========");
		List<Position> positionList = positionRepository.findAll();
		log.info("[PositionService] selectStorageTotal End ==========");
		
		return positionList.size();
	}
	
	/* 페이지 처리 */

	public Object selectPositionListWithPaging(Criteria cri) {
	log.info("[PositionService] selectPositionListWithPaging Start ==========");
		
		int index = cri.getPageNum() - 1;
        int count = cri.getAmount(); 
        Pageable paging = PageRequest.of(index, count, Sort.by("positionCode").descending());
	        
        Page<Position> result = positionRepository.findAll(paging);
        List<Position> positionList = (List<Position>)result.getContent();
        
        log.info("[PositionService] selectPositionListWithPaging End ============");
        
        return positionList.stream().map(position -> modelMapper.map(position, PositionDTO.class)).collect(Collectors.toList());

	}

	/* 직급 등록 */
	@Transactional
	public Object insertPosition(PositionDTO positionDTO) {
		log.info("[PositionService] ===== insertPosition Start =====");
		log.info("[PositionService] positionDTO : " + positionDTO);
        
		int result = 0;
		try {	
//			Storage storage = modelMapper.map(storageDTO, Storage.class);
			
			Position position = new Position();
			position.setPositionCode(positionDTO.getPositionCode());
			position.setPositionName(positionDTO.getPositionName());
			position.setPositionSalary(positionDTO.getPositionSalary());
			
			positionRepository.save(position);
			
			result = 1;
		} catch (Exception e) {
			log.info("[position] exception");
		}
		
		log.info("[PositionService] ====== insertPosition End ======");
		return (result > 0)? "등록 성공" : "등록 실패";
		}

	/* 직급 수정 */
	@Transactional
	public Object updatePosition(PositionDTO positionDTO) {
		log.info("[PositionService] ===== updatePosition Start =====");
		
		int result = 0;
		
		try {
			Position position= positionRepository.findById(positionDTO.getPositionCode()).get();
			position.setPositionCode(positionDTO.getPositionCode());
			position.setPositionName(positionDTO.getPositionName());
			position.setPositionSalary(positionDTO.getPositionSalary());
			
			result = 1;
		} catch (Exception e) {
			log.info("[review update] Exception!!");
		}
		
		log.info("[PositionService] ====== updatePosition End ======");
		return (result > 0) ? "직급 수정 성공" : "직급 수정 실패" ;
	}

	
	/* 직급 삭제 */
	@Transactional
	public Object deletePosition(Integer positionCode) {
		log.info("[PositionService] ===== deletePosition Start =====");
	    
	    int result = 0;
	    
	    try {
	        positionRepository.deleteById(positionCode);
	        result = 1;
	    } catch (Exception e) {
	        log.info("[position delete] Exception!!");
	    }
	    
	    log.info("[PositionService] ====== deletePosition End ======");
	    return (result > 0) ? "직급 삭제 성공" : "직급 삭제 실패";
	}

	@Transactional
	public Object deletePositions(List<Integer> positionCodes) {
		 positionRepository.deleteAllInBatch(positionRepository.findAllById(positionCodes));
		    return positionCodes;
		}


	
	/* 검색 */
	public Object searchPositionByName(String search) {
		log.info("[PositionService] searchPositionByName Start =====");
		
		List<Position> searchByName = positionRepository.findByPositionName(search);
		
		log.info("[PositionService] searchPositionByName End =======");
		
		return searchByName.stream().map(position -> modelMapper.map(position, PositionDTO.class)).collect(Collectors.toList());
	
	}	
	
	
	public Object selectPosition(int positionCode) {
		log.info("[PositionService] selectPosition Start ==========");
		
		Position position = positionRepository.findById(positionCode).get();
		
		log.info("[PositionService] selectPosition End ==========");
		
        PositionDTO positionDTO = modelMapper.map(position,PositionDTO.class);
        
		return positionDTO;
	}
	
	

	public List<PositionDTO> selectAllPosition() {
		log.info("[DeptService] selectAllPosition Start ==========");
		
		List<Position> positionList = positionRepository.findAll();
		
		log.info("[DeptService] selectAllPosition End ==========");
		
		return positionList.stream().map(position -> modelMapper.map(position, PositionDTO.class)).collect(Collectors.toList());
	}
	

}
