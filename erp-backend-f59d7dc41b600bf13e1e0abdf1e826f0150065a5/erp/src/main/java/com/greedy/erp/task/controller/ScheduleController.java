package com.greedy.erp.task.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greedy.erp.common.Criteria;
import com.greedy.erp.common.PageDTO;
import com.greedy.erp.common.PagingResponseDTO;
import com.greedy.erp.common.ResponseDTO;
import com.greedy.erp.production.business.dto.EstimateDTO;
import com.greedy.erp.task.dto.ScheduleDTO;
import com.greedy.erp.task.service.ScheduleService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("api/v1")
public class ScheduleController {
	
	private static final Logger log = LoggerFactory.getLogger(ScheduleController.class);
	
	private final ScheduleService scheduleService;

	@Autowired
	public ScheduleController(ScheduleService ScheduleService) {
		this.scheduleService = ScheduleService;	
	}
		
	/* 일정 */
	@Operation(summary = "일정 조회", description = "일정 목록 조회", tags = { "ScheduleController" })
	@GetMapping("/schedules")
	public ResponseEntity<ResponseDTO> selectSchedule(@RequestParam(name = "offset", defaultValue = "1") String offset) {
		log.info("[scheduleController] selectSchedule : " + offset);
		
		int total = scheduleService.selectScheduleTotal();
		
		Criteria cri = new Criteria(Integer.valueOf(offset), 10);
		PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
		pagingResponseDTO.setData(scheduleService.selectScheduleListWithPaging(cri));	
		pagingResponseDTO.setPageInfo(new PageDTO(cri, total));
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "일정 조회 성공", pagingResponseDTO));	
	}

	@Operation(summary = "일정 상세 조회", description = "일정 상세 조회 진행", tags = { "ScheduleController" })
    @GetMapping("/schedule/{scheduleCode}")
    public ResponseEntity<ResponseDTO> detailSchedule(@PathVariable int scheduleCode) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "일정 상세 조회 성공",  scheduleService.selectSchedule(scheduleCode)));
    }
	
	@Operation(summary = "일정 등록", description = "일정 등록 진행", tags = { "ScheduleController" })
    @PostMapping(value = "/schedules")
    public ResponseEntity<ResponseDTO> insertSchedule(@RequestBody ScheduleDTO scheduleDTO) {
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "일정 등록 성공",  scheduleService.insertSchedule(scheduleDTO)));
    }
	
	@Operation(summary = "일정 수정", description = "일정 수정 진행", tags = { "ScheduleController" })
    @PutMapping("/schedules")
    public ResponseEntity<ResponseDTO> updateSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "일정 수정 성공", scheduleService.updateSchedule(scheduleDTO)));
    }
	

}
