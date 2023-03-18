package com.greedy.erp.authority.entity;

import javax.persistence.*;

@Entity
@Table(name = "AUTHORITY")
@IdClass(EmpRolePk.class)
public class Authority {

	@Id
	@Column(name = "EMP_CODE")
	private int empCode;
	
	@Id
	@Column(name = "AUTH_CODE")
	private int authCode;
	
	/* Authority 타입의 속성은 조회할 때 Join용으로는 쓰지만 insert나 update할 때는 무시하라고 설정하자. */
	@ManyToOne
	@JoinColumn(name = "AUTH_CODE", insertable = false, updatable = false)
	private AuthorityRole authority;

	public Authority() {
		super();
	}

	public Authority(int empCode, int authCode, AuthorityRole authority) {
		super();
		this.empCode = empCode;
		this.authCode = authCode;
		this.authority = authority;
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

	public AuthorityRole getAuthority() {
		return authority;
	}

	public void setAuthority(AuthorityRole authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "EmpRole [empCode=" + empCode + ", authCode=" + authCode + ", authority=" + authority + "]";
	}

	
	
	
}