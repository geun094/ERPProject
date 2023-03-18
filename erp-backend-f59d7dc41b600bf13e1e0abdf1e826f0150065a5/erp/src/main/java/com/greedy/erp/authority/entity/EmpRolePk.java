package com.greedy.erp.authority.entity;

import java.io.Serializable;

/* 복합키 타입을 정의할 때는 Serializable을 반드시 구현 */
public class EmpRolePk implements Serializable {

	private int empCode;
	private int authCode;
	
	public EmpRolePk() {
		super();
	}

	public EmpRolePk(int empCode, int authCode) {
		super();
		this.empCode = empCode;
		this.authCode = authCode;
	}

	public int getEmpCode() {
		return empCode;
	}

	public void setEmpCode(int empCode) {
		this.empCode = empCode;
	}

	public int getAuthCode() {
		return authCode;
	}

	public void setAuthCode(int authCode) {
		this.authCode = authCode;
	}

	@Override
	public String toString() {
		return "EmpRolePk [empCode=" + empCode + ", authCode=" + authCode + "]";
	}
	
	
	
	
	
	
}