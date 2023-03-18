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

@Entity
@Table(name = "APPROVAL")
@SequenceGenerator(
		name = "APPROVAL_SEQ_GENERATOR",
		sequenceName = "SEQ_APPROVAL_CODE",
		initialValue = 1, allocationSize = 1
	)
public class JoinedApproval {

	@Id
	@Column(name = "APPROVAL_CODE")
	@GeneratedValue(
		strategy = GenerationType.SEQUENCE,
		generator = "APPROVAL_SEQ_GENERATOR"
	)
	private int approvalCode;
	
	@ManyToOne
	@JoinColumn(name = "EMP_CODE")
	private Emp emp;
	
	@ManyToOne
	@JoinColumn(name = "STATUS_CODE")
	private ApprovalStatus approvalStatus;
	
	@Column(name = "DOC_TYPE")
	private String docType;
	
	@Column(name = "APPROVAL_DATE")
	private Date approvalDate;
	
	@Column(name = "APPROVAL_TITLE")
	private String approvalTitle;
	
	@Column(name = "APPROVAL_CONTENT")
	private String approvalContent;
	
	@Column(name = "OLD_FILE_NAME")
	private String oldFileName;
	
	@Column(name = "NEW_FILE_NAME")
	private String newFileName;
	
	@Column(name = "LINK")
	private String link;
	
	@Column(name = "VACATION_TYPE")
	private String vacationType;
	
	@Column(name = "VACATION_START_DATE")
	private Date vacationStartDate;
	
	@Column(name = "VACATION_END_DATE")
	private Date vacationEndDate;

//	@OneToMany (cascade = CascadeType.ALL)
//	@JoinColumn(name = "LINE_CODE", nullable = false, insert="false", update="false")
//	private List<ApprovalLine> approvalLine;

	public JoinedApproval() {
		super();
	}

	public JoinedApproval(int approvalCode, Emp emp, ApprovalStatus approvalStatus, String docType, Date approvalDate,
			String approvalTitle, String approvalContent, String oldFileName, String newFileName, String link,
			String vacationType, Date vacationStartDate, Date vacationEndDate) {
		super();
		this.approvalCode = approvalCode;
		this.emp = emp;
		this.approvalStatus = approvalStatus;
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

	public Emp getEmp() {
		return emp;
	}

	public void setEmp(Emp emp) {
		this.emp = emp;
	}

	public ApprovalStatus getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(ApprovalStatus approvalStatus) {
		this.approvalStatus = approvalStatus;
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
		return "JoinedApproval [approvalCode=" + approvalCode + ", emp=" + emp + ", approvalStatus=" + approvalStatus
				+ ", docType=" + docType + ", approvalDate=" + approvalDate + ", approvalTitle=" + approvalTitle
				+ ", approvalContent=" + approvalContent + ", oldFileName=" + oldFileName + ", newFileName="
				+ newFileName + ", link=" + link + ", vacationType=" + vacationType + ", vacationStartDate="
				+ vacationStartDate + ", vacationEndDate=" + vacationEndDate + "]";
	}

}
