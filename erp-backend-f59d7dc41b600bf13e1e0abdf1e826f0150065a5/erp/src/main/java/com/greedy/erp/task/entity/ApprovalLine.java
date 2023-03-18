package com.greedy.erp.task.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.greedy.erp.regist.entity.Emp;

/* + Emp */
@Entity
@Table(name = "APPROVAL_LINE")
@SequenceGenerator(
	name = "APPROVAL_LINE_SEQ_GENERATOR",
	sequenceName = "SEQ_LINE_CODE",
	initialValue = 1, allocationSize = 1
)
public class ApprovalLine {

	@Id
	@GeneratedValue(
		strategy = GenerationType.SEQUENCE,
		generator = "APPROVAL_LINE_SEQ_GENERATOR"
	)
	@Column(name = "LINE_CODE")
	private int lineCode;
	
	@Column(name = "APPROVAL_CODE")
	private int approvalCode;
	
	@ManyToOne
	@JoinColumn(name = "EMP_CODE")
	private Emp emp;
	
	@Column(name = "APPROVER_ORDER")
	private int approverOrder;
	
	@Column(name = "APPROVED_DATE")
	private Date approvedDate;
	
	@Column(name = "APPROVE_YN")
	private String approveYn;
	
	@Column(name = "FINAL_YN")
	private String finalYn;

	public ApprovalLine() {
	}

	public ApprovalLine(int lineCode, int approvalCode, Emp emp, int approverOrder, Date approvedDate, String approveYn,
			String finalYn) {
		super();
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

	public Emp getEmp() {
		return emp;
	}

	public void setEmp(Emp emp) {
		this.emp = emp;
	}

	public int getApproverOrder() {
		return approverOrder;
	}

	public void setApproverOrder(int approverOrder) {
		this.approverOrder = approverOrder;
	}

	public Date getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
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
		return "ApprovalLine [lineCode=" + lineCode + ", approvalCode=" + approvalCode + ", emp=" + emp
				+ ", approverOrder=" + approverOrder + ", approvedDate=" + approvedDate + ", approveYn=" + approveYn
				+ ", finalYn=" + finalYn + "]";
	}

}
