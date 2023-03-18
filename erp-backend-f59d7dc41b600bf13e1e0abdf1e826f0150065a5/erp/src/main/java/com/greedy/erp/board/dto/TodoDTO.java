package com.greedy.erp.board.dto;

import java.util.Date;

import com.greedy.erp.regist.dto.EmpDTO;

public class TodoDTO {

	private int todoCode;
	private EmpDTO emp;
	private Date todoDate;
	private String todoContent;
	private String completeYn;
	private Date todoDeadline;
	
	
	public TodoDTO() {
		
	}

	public TodoDTO(int todoCode, EmpDTO emp, Date todoDate, String todoContent, String completeYn, Date todoDeadline) {
		super();
		this.todoCode = todoCode;
		this.emp = emp;
		this.todoDate = todoDate;
		this.todoContent = todoContent;
		this.completeYn = completeYn;
		this.todoDeadline = todoDeadline;
	}


	public int getTodoCode() {
		return todoCode;
	}


	public void setTodoCode(int todoCode) {
		this.todoCode = todoCode;
	}


	public EmpDTO getEmp() {
		return emp;
	}


	public void setEmp(EmpDTO emp) {
		this.emp = emp;
	}


	public Date getTodoDate() {
		return todoDate;
	}


	public void setTodoDate(Date todoDate) {
		this.todoDate = todoDate;
	}


	public String getTodoContent() {
		return todoContent;
	}


	public void setTodoContent(String todoContent) {
		this.todoContent = todoContent;
	}


	public String getCompleteYn() {
		return completeYn;
	}


	public void setCompleteYn(String completeYn) {
		this.completeYn = completeYn;
	}


	public Date getTodoDeadline() {
		return todoDeadline;
	}


	public void setTodoDeadline(Date todoDeadline) {
		this.todoDeadline = todoDeadline;
	}


	@Override
	public String toString() {
		return "TodoDTO [todoCode=" + todoCode + ", emp=" + emp + ", todoDate=" + todoDate + ", todoContent="
				+ todoContent + ", completeYn=" + completeYn + ", todoDeadline=" + todoDeadline + "]";
	}
	
}
