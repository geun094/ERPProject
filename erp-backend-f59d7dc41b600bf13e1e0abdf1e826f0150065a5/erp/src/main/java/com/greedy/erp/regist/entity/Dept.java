package com.greedy.erp.regist.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "DEPT")
//@SequenceGenerator(
//		name = "DEPT_SEQ_GENERATOR",
//		sequenceName = "SEQ_DEPT_CODE",
//		initialValue = 1, allocationSize = 1
//		)
public class Dept {
	
	@Id
	@Column(name = "DEPT_CODE")
//	@GeneratedValue(
//			strategy = GenerationType.SEQUENCE,
//			generator = "DEPT_SEQ_GENERATOR"
//		)
	private int deptCode;
	
	@Column(name = "DEPT_NAME")
	private String deptName;
	
	@Column(name = "DEPT_SALARY")
	private int deptSalary;
	
	public Dept() {}

	public Dept(int deptCode, String deptName, int deptSalary) {
		super();
		this.deptCode = deptCode;
		this.deptName = deptName;
		this.deptSalary = deptSalary;
	}

	public int getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(int deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public int getDeptSalary() {
		return deptSalary;
	}

	public void setDeptSalary(int deptSalary) {
		this.deptSalary = deptSalary;
	}

	@Override
	public String toString() {
		return "DeptDTO [deptCode=" + deptCode + ", deptName=" + deptName + ", deptSalary=" + deptSalary + "]";
	}
	
}
