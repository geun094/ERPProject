package com.greedy.erp.authority.entity;

import javax.persistence.*;

@Entity
@Table(name = "AUTHORITYROLE")
public class AuthorityRole {
	
	@Id
	@Column(name = "AUTH_CODE")
	private int authCode;
	
	@Column(name = "AUTH_NAME")
	private String authName;
	
	@Column(name = "AUTH_DESC")
	private String authDesc;

	public AuthorityRole() {
		super();
	}

	public AuthorityRole(int authCode, String authName, String authDesc) {
		super();
		this.authCode = authCode;
		this.authName = authName;
		this.authDesc = authDesc;
	}

	public int getAuthCode() {
		return authCode;
	}

	public void setAuthCode(int authCode) {
		this.authCode = authCode;
	}

	public String getAuthName() {
		return authName;
	}

	public void setAuthName(String authName) {
		this.authName = authName;
	}

	public String getAuthDesc() {
		return authDesc;
	}

	public void setAuthDesc(String authDesc) {
		this.authDesc = authDesc;
	}

	@Override
	public String toString() {
		return "Authority [authCode=" + authCode + ", authName=" + authName + ", authDesc=" + authDesc + "]";
	}

	
	
	

}
