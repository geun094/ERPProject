package com.greedy.erp.task.entity;

import java.util.Date;

import javax.persistence.CascadeType;
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
@Table(name = "SCHEDULE")
@SequenceGenerator(
	name = "SCHEDULE_SEQ_GENERATOR",
	sequenceName = "SEQ_SCHEDULE_CODE",
	initialValue = 1, allocationSize = 1
)
public class Schedule {
	
	@Id
	@Column(name = "SCHEDULE_CODE")
	@GeneratedValue(
		strategy = GenerationType.SEQUENCE,
		generator = "SCHEDULE_SEQ_GENERATOR"
	)
	private int scheduleCode;
	
	@ManyToOne
	@JoinColumn( name = "EMP_CODE")
	private Emp emp;
	
	
	@Column(name = "SCHEDULE_STATUS")
	private String scheduleStatus;
	
	@Column(name = "START_DATE")
	private Date startDate;
	
	@Column(name = "END_DATE")
	private Date endDate;
	
	@Column(name = "SCHEDULE_TITLE")
	private String scheduleTitle;
	
	@Column(name = "SCHEDULE_CONTENT")
	private String scheduleContent;
	
	@Column(name = "LOCATION")
	private String location;
	
	@Column(name = "SCHEDULE_TIME")
	private String scheduleTime;
	
	@Column(name = "OPEN_SCOPE")
	private String openScope;
	
	public Schedule() {
	}

	public Schedule(int scheduleCode, Emp emp, String scheduleStatus, Date startDate, Date endDate,
			String scheduleTitle, String scheduleContent, String location, String scheduleTime, String openScope) {
		super();
		this.scheduleCode = scheduleCode;
		this.emp = emp;
		this.scheduleStatus = scheduleStatus;
		this.startDate = startDate;
		this.endDate = endDate;
		this.scheduleTitle = scheduleTitle;
		this.scheduleContent = scheduleContent;
		this.location = location;
		this.scheduleTime = scheduleTime;
		this.openScope = openScope;
	}

	public int getScheduleCode() {
		return scheduleCode;
	}

	public void setScheduleCode(int scheduleCode) {
		this.scheduleCode = scheduleCode;
	}

	public Emp getEmp() {
		return emp;
	}

	public void setEmp(Emp emp) {
		this.emp = emp;
	}

	public String getScheduleStatus() {
		return scheduleStatus;
	}

	public void setScheduleStatus(String scheduleStatus) {
		this.scheduleStatus = scheduleStatus;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getScheduleTitle() {
		return scheduleTitle;
	}

	public void setScheduleTitle(String scheduleTitle) {
		this.scheduleTitle = scheduleTitle;
	}

	public String getScheduleContent() {
		return scheduleContent;
	}

	public void setScheduleContent(String scheduleContent) {
		this.scheduleContent = scheduleContent;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getScheduleTime() {
		return scheduleTime;
	}

	public void setScheduleTime(String scheduleTime) {
		this.scheduleTime = scheduleTime;
	}

	public String getOpenScope() {
		return openScope;
	}

	public void setOpenScope(String openScope) {
		this.openScope = openScope;
	}

	@Override
	public String toString() {
		return "Schedule [scheduleCode=" + scheduleCode + ", emp=" + emp + ", scheduleStatus=" + scheduleStatus
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", scheduleTitle=" + scheduleTitle
				+ ", scheduleContent=" + scheduleContent + ", location=" + location + ", scheduleTime=" + scheduleTime
				+ ", openScope=" + openScope + "]";
	}

}
