package com.greedy.erp.task.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "APPROVAL_STATUS")
public class ApprovalStatus {

	@Id
	@Column(name = "STATUS_CODE")
	private int statusCode;

	@Column(name = "STATUS_DESC")
	private String statusDesc;

	public ApprovalStatus() {
	}

	public ApprovalStatus(int statusCode, String statusDesc) {
		this.statusCode = statusCode;
		this.statusDesc = statusDesc;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	@Override
	public String toString() {
		return "ApprovalStatus [statusCode=" + statusCode + ", statusDesc=" + statusDesc + "]";
	}
	
}
