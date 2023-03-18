package com.greedy.erp.task.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.greedy.erp.authority.repository.EmpRepository;
import com.greedy.erp.common.Criteria;
import com.greedy.erp.regist.dto.EmpDTO;
import com.greedy.erp.regist.entity.Emp;
import com.greedy.erp.task.dto.ApprovalLineDTO;
import com.greedy.erp.task.entity.ApprovalLine;
import com.greedy.erp.task.entity.JoinedApproval;
import com.greedy.erp.task.entity.Schedule;
import com.greedy.erp.task.repository.ApprovalLineRepository;
import com.greedy.erp.task.repository.ApprovalStatusRepository;
import com.greedy.erp.task.repository.JoinedApprovalRepository;
import com.greedy.erp.task.repository.ScheduleRepository;

@Service
public class ApprovalLineService {

	private static final Logger log = LoggerFactory.getLogger(ApprovalLineService.class);
	private final ApprovalLineRepository approvalLineRepository;
	private final JoinedApprovalRepository joinedApprovalRepository;
	private final ApprovalStatusRepository approvalStatusRepository;
	private final ScheduleRepository scheduleRepository;
	private final EmpRepository empRepository;
	private final ModelMapper modelMapper;
	
    @Value("${image.image-dir}")
    private String IMAGE_DIR;
    @Value("${image.image-url}")
    private String IMAGE_URL;
    
	@Autowired
    public ApprovalLineService(ApprovalLineRepository approvalLineRepository, ApprovalStatusRepository approvalStatusRepository, 
    		JoinedApprovalRepository joinedApprovalRepository, EmpRepository empRepository, ScheduleRepository scheduleRepository,
    		ModelMapper modelMapper) {
		this.approvalLineRepository = approvalLineRepository;
		this.joinedApprovalRepository = joinedApprovalRepository;
		this.approvalStatusRepository = approvalStatusRepository;
		this.scheduleRepository = scheduleRepository;
		this.empRepository = empRepository;
		this.modelMapper = modelMapper;
	}

	/* 결재코드로 라인 조회 */
	public Object selectApprovalLineByApproval(int approvalCode) {
		List<ApprovalLine> approvalLineList = approvalLineRepository.findByApprovalCodeOrderByApproverOrderAsc(approvalCode);
		for (int i = 0; i < approvalLineList.size(); i++) {
			approvalLineList.get(i).getEmp().setEmpStamp(IMAGE_URL + "stampimgs/" + approvalLineList.get(i).getEmp().getEmpStamp());
		}
		return approvalLineList.stream().map(approvalLine -> modelMapper.map(approvalLine, ApprovalLineDTO.class)).collect(Collectors.toList());
	}
	
	/* 결재 승인하기 */
	@Transactional
	public Object updateApproveYn(int empCode, int approvalCode) {
		ApprovalLine approvalLine = approvalLineRepository.updateApproveYn(empCode, approvalCode);
		approvalLine.setApproveYn("Y");
		approvalLine.setApprovedDate(new Date(System.currentTimeMillis()));
		JoinedApproval approval = joinedApprovalRepository.findById(approvalCode).get();
		log.info("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
		log.info(approval.getDocType());
		log.info("휴가신청서".equals(approval.getDocType()) + "");
		if("Y".equals(approvalLine.getFinalYn())) {
			approval.setApprovalStatus(approvalStatusRepository.findById(3).get());
			if("휴가신청서".equals(approval.getDocType())) {
				Schedule schedule = new Schedule();
				Emp emp = empRepository.findById(approval.getEmp().getEmpCode()).get();
				schedule.setEmp(emp);
				schedule.setStartDate(approval.getVacationStartDate());
				schedule.setEndDate(approval.getVacationEndDate());
				schedule.setScheduleTitle(emp.getEmpName() + " " + approval.getVacationType());
				schedule.setScheduleContent(approval.getApprovalContent());
				scheduleRepository.save(schedule);
				log.info(schedule + "");
			}
		} else {
			approval.setApprovalStatus(approvalStatusRepository.findById(1).get());
		}
		return modelMapper.map(approvalLine, ApprovalLineDTO.class);
	}

	/* 전체 사원 목록 조회 */
	public int selectApproverTotal() {
		List<Emp> approverList = empRepository.findAll();
		return approverList.size();
	}
	public Object selectApproverList(Criteria cri) {
		int index = cri.getPageNum() - 1;
	    int count = cri.getAmount(); 
	    Pageable paging = PageRequest.of(index, count, Sort.by("empCode"));
	    Page<Emp> result = empRepository.findAll(paging);
	    List<Emp> approverList = (List<Emp>)result.getContent();
	    return approverList.stream().map(approver -> modelMapper.map(approver, EmpDTO.class)).collect(Collectors.toList());
	}

	/* 사원명으로 검색 */
	public Object searchApproverByName(String search) {
		List<Emp> searchedApprovers = empRepository.findByEmpNameContaining(search);
		return searchedApprovers.stream().map(approver -> modelMapper.map(approver, EmpDTO.class)).collect(Collectors.toList());
	}
	


}
