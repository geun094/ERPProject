package com.greedy.erp.production.production.service;

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

import com.greedy.erp.authority.repository.EmpRepository;
import com.greedy.erp.common.Criteria;
import com.greedy.erp.production.production.dto.InstructionDTO;
import com.greedy.erp.production.production.entity.Instruction;
import com.greedy.erp.production.production.repository.InstructionRepository;
import com.greedy.erp.regist.repository.ClientRepository;
import com.greedy.erp.regist.repository.ProductRepository;

@Service
public class InstructionService {

	
	private static final Logger log = LoggerFactory.getLogger(InstructionService.class);
	private final InstructionRepository instructionRepository;
	private final ModelMapper modelMapper;
	private final ClientRepository clientRepository;
	private final EmpRepository empRepository;
	private final ProductRepository productRepository;
	
	@Autowired
    public InstructionService(InstructionRepository instructionRepository, ModelMapper modelMapper,
    						  ClientRepository clientRepository, EmpRepository empRepository,
    						  ProductRepository productpository) {
		this.instructionRepository = instructionRepository;
		this.modelMapper = modelMapper;
		this.clientRepository = clientRepository;
		this.empRepository = empRepository;
		this.productRepository = productpository;
	}
	
	/* 조회 페이징 */
	public int selectInstructionTotal() {
		log.info("[InstructionService] selectInstructionTotal Start ==========");
		List<Instruction> instructionList = instructionRepository.findAll();
		log.info("[InstructionService] selectInstructionTotal End ==========");
		
		return instructionList.size();
	}
	
	/* 지시 조회 */
	public Object selectInstructionListWithPaging(Criteria cri) {
		log.info("[InstructionService] selectInstructionListWithPaging Start ==========");
		
		int index = cri.getPageNum() - 1;
	    int count = cri.getAmount(); 
	    Pageable paging = PageRequest.of(index, count, Sort.by("instructionCode").descending());
	        
	    Page<Instruction> result = instructionRepository.findAll(paging);
	    List<Instruction> instructionList = (List<Instruction>)result.getContent();
	    
	    log.info("[InstructionService] selectInstructionListWithPaging End ============");
    
    return instructionList.stream().map(instruction -> modelMapper.map(instruction, InstructionDTO.class)).collect(Collectors.toList());
	}
	
	/* 지시 등록 */
	@Transactional
	public Object insertInstruction(InstructionDTO instructionDTO) {
		log.info("[InstructionService] ===== insertInstruction Start =====");
		log.info("[InstructionService] instructionDTO : " + instructionDTO);

		int result = 0;
		
		try {	
			Instruction instruction = modelMapper.map(instructionDTO, Instruction.class);
			instructionRepository.save(instruction);

			result = 1;
		} catch (Exception e) {
			throw new RuntimeException(e);
//			log.info("[instruction] exception");
		}
		
		log.info("[InstructionService] ====== insertInstruction End ======");
		return (result > 0)? "등록 성공" : "등록 실패";
		}
	
	
	/* 지시 상세 조회 */
	public Object selectInstruction(int instructionCode) {
		log.info("[InstructionService] selectInstruction Start =====");
		
		Instruction instruction = instructionRepository.findById(instructionCode).get();
		
		log.info("[InstructionService] selectInstruction End =======");
		
        InstructionDTO instructionDTO = modelMapper.map(instruction, InstructionDTO.class);
        
		return instructionDTO;
	}

	/* 지시서 수정 */
	@Transactional
	public Object updateInstruction(InstructionDTO instructionDTO) {
		log.info("[InstructionService] ===== updateInstruction Start =====");
		
		int result = 0;
		
		try {
			Instruction instruction = instructionRepository.findById(instructionDTO.getInstructionCode()).get();
			instruction.setInstructionCode(instructionDTO.getInstructionCode());
			instruction.setInstructionDelivery(instructionDTO.getInstructionDelivery());
			instruction.setClient(clientRepository.findByClientCode(instructionDTO.getClient().getClientCode()));
			instruction.setEmp(empRepository.findByEmpCode(instructionDTO.getEmp().getEmpCode()));
			instruction.setInstructionDetail(instructionDTO.getInstructionDetail());
			
			result = 1;
		} catch (Exception e) {
			log.info("[review update] Exception!!");
		}
		
		log.info("[InstructionService] ====== updateInstruction End ======");
		return (result > 0) ? "지시서 수정 성공" : "지시서 수정 실패" ;
	}
	
}
