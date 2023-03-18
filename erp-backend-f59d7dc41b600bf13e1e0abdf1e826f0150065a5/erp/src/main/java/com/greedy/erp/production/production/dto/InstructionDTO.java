package com.greedy.erp.production.production.dto;

import java.sql.Date;
import java.util.List;

import com.greedy.erp.production.production.entity.InstructionDetail;
import com.greedy.erp.regist.dto.ClientDTO;
import com.greedy.erp.regist.dto.EmpDTO;

public class InstructionDTO {

	private int instructionCode;
	private Date instructionDate;
	private Date instructionDelivery;
	
	private EmpDTO emp;
	private ClientDTO client;
	
	private List<InstructionDetail> instructionDetail;

	public InstructionDTO() {
	}

	public InstructionDTO(int instructionCode, Date instructionDate, Date instructionDelivery, EmpDTO emp,
			ClientDTO client, List<InstructionDetail> instructionDetail) {
		this.instructionCode = instructionCode;
		this.instructionDate = instructionDate;
		this.instructionDelivery = instructionDelivery;
		this.emp = emp;
		this.client = client;
		this.instructionDetail = instructionDetail;
	}

	public int getInstructionCode() {
		return instructionCode;
	}

	public void setInstructionCode(int instructionCode) {
		this.instructionCode = instructionCode;
	}

	public Date getInstructionDate() {
		return instructionDate;
	}

	public void setInstructionDate(Date instructionDate) {
		this.instructionDate = instructionDate;
	}

	public Date getInstructionDelivery() {
		return instructionDelivery;
	}

	public void setInstructionDelivery(Date instructionDelivery) {
		this.instructionDelivery = instructionDelivery;
	}

	public EmpDTO getEmp() {
		return emp;
	}

	public void setEmp(EmpDTO emp) {
		this.emp = emp;
	}

	public ClientDTO getClient() {
		return client;
	}

	public void setClient(ClientDTO client) {
		this.client = client;
	}

	public List<InstructionDetail> getInstructionDetail() {
		return instructionDetail;
	}

	public void setInstructionDetail(List<InstructionDetail> instructionDetail) {
		this.instructionDetail = instructionDetail;
	}

	@Override
	public String toString() {
		return "InstructionDTO [instructionCode=" + instructionCode + ", instructionDate=" + instructionDate
				+ ", instructionDelivery=" + instructionDelivery + ", emp=" + emp + ", client=" + client
				+ ", instructionDetail=" + instructionDetail + "]";
	}
	
	
	
}
