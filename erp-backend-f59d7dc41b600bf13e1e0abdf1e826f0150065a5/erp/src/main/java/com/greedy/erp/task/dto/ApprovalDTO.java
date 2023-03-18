package com.greedy.erp.task.dto;

import java.util.Date;
import java.util.List;

import com.greedy.erp.authority.dto.EmpRoleDTO;
import com.greedy.erp.production.business.entity.EstimateDetail;
import com.greedy.erp.regist.dto.EmpDTO;


/* + EmpDTO + ApprovalStatusDTO */
public class ApprovalDTO {
	
	private int approvalCode;
	
//	private EmpDTO emp;
	private int empCode;
	
//	private ApprovalStatusDTO approvalStatus;
	private int statusCode;
	
	private String docType;
	private Date approvalDate;
	private String approvalTitle;
	private String approvalContent;
	private String oldFileName;
	private String newFileName;
	private String link;
	private String vacationType;
	private Date vacationStartDate;
	private Date vacationEndDate;
//	private List<ApprovalLine> approvalLine;
	
	public ApprovalDTO() {
	}
	
	public ApprovalDTO(int approvalCode, int empCode, int statusCode, String docType, Date approvalDate,
			String approvalTitle, String approvalContent, String oldFileName, String newFileName, String link,
			String vacationType, Date vacationStartDate, Date vacationEndDate) {
		super();
		this.approvalCode = approvalCode;
		this.empCode = empCode;
		this.statusCode = statusCode;
		this.docType = docType;
		this.approvalDate = approvalDate;
		this.approvalTitle = approvalTitle;
		this.approvalContent = approvalContent;
		this.oldFileName = oldFileName;
		this.newFileName = newFileName;
		this.link = link;
		this.vacationType = vacationType;
		this.vacationStartDate = vacationStartDate;
		this.vacationEndDate = vacationEndDate;
	}

	public int getApprovalCode() {
		return approvalCode;
	}

	public void setApprovalCode(int approvalCode) {
		this.approvalCode = approvalCode;
	}

	public int getEmpCode() {
		return empCode;
	}

	public void setEmpCode(int empCode) {
		this.empCode = empCode;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	public String getApprovalTitle() {
		return approvalTitle;
	}

	public void setApprovalTitle(String approvalTitle) {
		this.approvalTitle = approvalTitle;
	}

	public String getApprovalContent() {
		return approvalContent;
	}

	public void setApprovalContent(String approvalContent) {
		this.approvalContent = approvalContent;
	}

	public String getOldFileName() {
		return oldFileName;
	}

	public void setOldFileName(String oldFileName) {
		this.oldFileName = oldFileName;
	}

	public String getNewFileName() {
		return newFileName;
	}

	public void setNewFileName(String newFileName) {
		this.newFileName = newFileName;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getVacationType() {
		return vacationType;
	}

	public void setVacationType(String vacationType) {
		this.vacationType = vacationType;
	}

	public Date getVacationStartDate() {
		return vacationStartDate;
	}

	public void setVacationStartDate(Date vacationStartDate) {
		this.vacationStartDate = vacationStartDate;
	}

	public Date getVacationEndDate() {
		return vacationEndDate;
	}

	public void setVacationEndDate(Date vacationEndDate) {
		this.vacationEndDate = vacationEndDate;
	}

	@Override
	public String toString() {
		return "ApprovalDTO [approvalCode=" + approvalCode + ", empCode=" + empCode + ", statusCode=" + statusCode
				+ ", docType=" + docType + ", approvalDate=" + approvalDate + ", approvalTitle=" + approvalTitle
				+ ", approvalContent=" + approvalContent + ", oldFileName=" + oldFileName + ", newFileName="
				+ newFileName + ", link=" + link + ", vacationType=" + vacationType + ", vacationStartDate="
				+ vacationStartDate + ", vacationEndDate=" + vacationEndDate + "]";
	}
	

	
}
