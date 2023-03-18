package com.greedy.erp.task.dto;

import com.greedy.erp.regist.dto.EmpDTO;

/* + ApprovalDTO + EmpDTO */
public class ApprovalLineDTO {

	private int lineCode;
	private int approvalCode;
	private EmpDTO emp;
	private int approverOrder;
	private String approvedDate;
	private String approveYn;
	private String finalYn;
	
	public ApprovalLineDTO() {
	}

	public ApprovalLineDTO(int lineCode, int approvalCode, EmpDTO emp, int approverOrder, String approvedDate,
			String approveYn, String finalYn) {
		this.lineCode = lineCode;
		this.approvalCode = approvalCode;
		this.emp = emp;
		this.approverOrder = approverOrder;
		this.approvedDate = approvedDate;
		this.approveYn = approveYn;
		this.finalYn = finalYn;
	}

	public int getLineCode() {
		return lineCode;
	}

	public void setLineCode(int lineCode) {
		this.lineCode = lineCode;
	}

	public int getApprovalCode() {
		return approvalCode;
	}

	public void setApprovalCode(int approvalCode) {
		this.approvalCode = approvalCode;
	}

	public EmpDTO getEmp() {
		return emp;
	}

	public void setEmp(EmpDTO emp) {
		this.emp = emp;
	}

	public int getApproverOrder() {
		return approverOrder;
	}

	public void setApproverOrder(int approverOrder) {
		this.approverOrder = approverOrder;
	}

	public String getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(String approvedDate) {
		this.approvedDate = approvedDate;
	}

	public String getApproveYn() {
		return approveYn;
	}

	public void setApproveYn(String approveYn) {
		this.approveYn = approveYn;
	}

	public String getFinalYn() {
		return finalYn;
	}

	public void setFinalYn(String finalYn) {
		this.finalYn = finalYn;
	}

	@Override
	public String toString() {
		return "ApprovalLineDTO [lineCode=" + lineCode + ", approvalCode=" + approvalCode + ", emp=" + emp
				+ ", approverOrder=" + approverOrder + ", approvedDate=" + approvedDate + ", approveYn=" + approveYn
				+ ", finalYn=" + finalYn + "]";
	}

}
