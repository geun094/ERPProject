package com.greedy.erp.task.service;

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
import com.greedy.erp.production.business.dto.OrdersDTO;
import com.greedy.erp.production.business.entity.Orders;
import com.greedy.erp.task.dto.ScheduleDTO;
import com.greedy.erp.task.entity.Schedule;
import com.greedy.erp.task.repository.ScheduleRepository;

@Service	
public class ScheduleService {

	private static final Logger log = LoggerFactory.getLogger(ScheduleService.class);
	private final ScheduleRepository scheduleRepository;
	private final EmpRepository empRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
    public ScheduleService(ScheduleRepository scheduleRepository, EmpRepository empRepository, ModelMapper modelMapper) {
		this.scheduleRepository = scheduleRepository;
		this.empRepository = empRepository;
		this.modelMapper = modelMapper;
	}
	
	public int selectScheduleTotal() {
		log.info("[ScheduleService] selectScheduleTotal Start ==========");
		List<Schedule> scheduleList = scheduleRepository.findAll();
		log.info("[ScheduleService] selectScheduleTotal End ==========");
		return scheduleList.size();
	}
	
	public Object selectScheduleListWithPaging(Criteria cri) {
		log.info("[ScheduleService] selectScheduleListWithPaging Start ==========");
		int index = cri.getPageNum() - 1;
	    int count = cri.getAmount(); 
	    Pageable paging = PageRequest.of(index, count, Sort.by("scheduleCode").descending());
	    Page<Schedule> result = scheduleRepository.findAll(paging);
	    List<Schedule> scheduleList = (List<Schedule>)result.getContent();
	    log.info("[ScheduleService] selectScheduleListWithPaging End ============");
    return scheduleList.stream().map(schedule -> modelMapper.map(schedule, ScheduleDTO.class)).collect(Collectors.toList());
	}
	
	public Object selectSchedule(int scheduleCode) {
		log.info("[ScheduleService] selectSchedule Start =====");
		Schedule schedule = scheduleRepository.findById(scheduleCode).get();
		log.info("[ScheduleService] selectSchedule End =======");
        ScheduleDTO scheduleDTO = modelMapper.map(schedule, ScheduleDTO.class);
		return scheduleDTO;
	}
	
	@Transactional
	public Object insertSchedule(ScheduleDTO scheduleDTO) {
		log.info("[ScheduleService] ===== insertSchedule Start =====");
		log.info("[ScheduleService] scheduleDTO : " + scheduleDTO);
		int result = 0;
		try {	
			Schedule schedule = modelMapper.map(scheduleDTO, Schedule.class);
			scheduleRepository.save(schedule);
			result = 1;
		} catch (Exception e) {
			log.info("[schedule] exception");
		}
		log.info("[ScheduleService] ====== insertSchedule End ======");
		return (result > 0)? "등록 성공" : "등록 실패";
		}
	
	@Transactional
	public Object updateSchedule(ScheduleDTO scheduleDTO) {
		log.info("[ScheduleService] ===== updateSchedule Start =====");
		int result = 0;
		try {
			Schedule schedule = scheduleRepository.findById(scheduleDTO.getScheduleCode()).get();
			schedule.setScheduleCode(scheduleDTO.getScheduleCode());
			schedule.setEmp(empRepository.findByEmpCode(scheduleDTO.getEmp().getEmpCode()));
			schedule.setScheduleStatus(scheduleDTO.getScheduleStatus());
			schedule.setStartDate(scheduleDTO.getStartDate());
			schedule.setEndDate(scheduleDTO.getEndDate());
			schedule.setScheduleTitle(scheduleDTO.getScheduleTitle());
			schedule.setScheduleContent(scheduleDTO.getScheduleContent());
			schedule.setLocation(scheduleDTO.getLocation());
			schedule.setScheduleTime(scheduleDTO.getScheduleTime());
			schedule.setOpenScope(scheduleDTO.getOpenScope());
			
			result = 1;
		} catch (Exception e) {
			log.info("[review update] Exception!!");
		}
		log.info("[ScheduleService] ====== updateSchedule End ======");
		return (result > 0) ? "일정 수정 성공" : "일정 수정 실패" ;
	}
	
	
}
