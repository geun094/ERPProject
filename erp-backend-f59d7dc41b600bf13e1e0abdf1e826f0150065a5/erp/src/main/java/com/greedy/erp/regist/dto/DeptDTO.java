package com.greedy.erp.regist.dto;

public class DeptDTO {
	private int deptCode;
	private String deptName;
	private int deptSalary;
	
	public DeptDTO() {}

	public DeptDTO(int deptCode, String deptName, int deptSalary) {
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
