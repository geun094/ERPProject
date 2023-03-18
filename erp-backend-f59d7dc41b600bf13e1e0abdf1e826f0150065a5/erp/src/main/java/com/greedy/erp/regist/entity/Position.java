package com.greedy.erp.regist.entity;

import javax.persistence.*;
import java.util.Date;



@Entity
@Table(name = "POSITION")
@SequenceGenerator(
		name = "POSITION_SEQ_GENERATOR",
		sequenceName = "SEQ_POSITION_CODE",
		initialValue = 1, allocationSize = 1
		)
public class Position {
	
	@Id
	@Column(name = "POSITION_CODE")
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "POSITION_SEQ_GENERATOR"
		)
	private int positionCode;
	
	@Column(name = "POSITION_NAME")
	private String positionName;
	
	@Column(name = "POSITION_SALARY")
	private int positionSalary;
	
	public Position() {}

	public Position(int positionCode, String positionName, int positionSalary) {
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