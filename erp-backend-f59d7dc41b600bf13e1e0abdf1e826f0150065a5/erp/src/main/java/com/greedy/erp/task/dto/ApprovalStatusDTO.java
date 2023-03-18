package com.greedy.erp.task.dto;

public class ApprovalStatusDTO {
	
	private int statusCode;
	private String statusDesc;
	
	public ApprovalStatusDTO() {
	}
	
	public ApprovalStatusDTO(int statusCode, String statusDesc) {
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
		return "ApprovalStatusDTO [statusCode=" + statusCode + ", statusDesc=" + statusDesc + "]";
	}
		
}
