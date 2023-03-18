package com.greedy.erp.task.dto;

import java.util.Date;

import com.greedy.erp.regist.dto.EmpDTO;

/* + empDTO */
public class ScheduleDTO {

	private int scheduleCode;
	private EmpDTO emp;
	private String scheduleStatus;
	private Date startDate;
	private Date endDate;
	private String scheduleTitle;
	private String scheduleContent;
	private String location;
	private String scheduleTime;
	private String openScope;
	
	public ScheduleDTO() {
	}

	public ScheduleDTO(int scheduleCode, EmpDTO emp, String scheduleStatus, Date startDate, Date endDate,
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

	public EmpDTO getEmp() {
		return emp;
	}

	public void setEmp(EmpDTO emp) {
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
		return "ScheduleDTO [scheduleCode=" + scheduleCode + ", emp=" + emp + ", scheduleStatus=" + scheduleStatus
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", scheduleTitle=" + scheduleTitle
				+ ", scheduleContent=" + scheduleContent + ", location=" + location + ", scheduleTime=" + scheduleTime
				+ ", openScope=" + openScope + "]";
	}

}
