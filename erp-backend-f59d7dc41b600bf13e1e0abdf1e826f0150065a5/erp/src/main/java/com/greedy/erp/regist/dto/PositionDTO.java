package com.greedy.erp.regist.dto;

import java.util.Date;

public class PositionDTO {
	private int positionCode;
	private String positionName;
	private int positionSalary;
	
	public PositionDTO() {}

	public PositionDTO(int positionCode, String positionName, int positionSalary) {
		super();
		this.positionCode = positionCode;
		this.positionName = positionName;
		this.positionSalary = positionSalary;
	}

	public int getPositionCode() {
		return positionCode;
	}

	public void setPositionCode(int positionCode) {
		this.positionCode = positionCode;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public int getPositionSalary() {
		return positionSalary;
	}

	public void setPositionSalary(int positionSalary) {
		this.positionSalary = positionSalary;
	}

	@Override
	public String toString() {
		return "PositionDTO [positionCode=" + positionCode + ", positionName=" + positionName + ", positionSalary="
				+ positionSalary + "]";
	}

}