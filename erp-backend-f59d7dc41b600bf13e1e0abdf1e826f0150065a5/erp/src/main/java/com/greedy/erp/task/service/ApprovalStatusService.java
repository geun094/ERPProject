package com.greedy.erp.task.service;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.erp.task.repository.ApprovalStatusRepository;

@Service
public class ApprovalStatusService {

	private static final Logger log = LoggerFactory.getLogger(ApprovalStatusService.class);
	private final ApprovalStatusRepository approvalStatusRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
    public ApprovalStatusService(ApprovalStatusRepository approvalStatusRepository, ModelMapper modelMapper) {
		this.approvalStatusRepository = approvalStatusRepository;
		this.modelMapper = modelMapper;
	}
}
