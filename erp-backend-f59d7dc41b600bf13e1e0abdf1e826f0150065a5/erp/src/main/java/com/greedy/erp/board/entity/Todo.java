package com.greedy.erp.board.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.greedy.erp.regist.entity.Emp;



@Entity
@Table(name = "TODO")
@SequenceGenerator(
		name = "TODO_SEQ_GENERATOR",
		sequenceName = "SEQ_TODO_CODE",
		initialValue = 1, allocationSize = 1
	)
public class Todo {
	
	@Id
	@Column(name = "TODO_CODE")
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "TODO_SEQ_GENERATOR"
		)
	private int todoCode;
	
	@ManyToOne
	@JoinColumn(name = "EMP_CODE")
	private Emp emp;
	
	@Column(name = "TODO_DATE")
	private Date todoDate;
	
	@Column(name = "TODO_CONTENT")
	private String todoContent;
	
	@Column(name = "COMPLETE_YN")
	private String completeYn;
	
	@Column(name = "TODO_DEADLINE")
	private Date todoDeadline;
	
	
	public Todo() {
		
	}
	
	
	public Todo(int todoCode, Emp emp, Date todoDate, String todoContent, String completeYn, Date todoDeadline) {
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

	public Emp getEmp() {
		return emp;
	}

	public void setEmp(Emp emp) {
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
		return "Todo [todoCode=" + todoCode + ", emp=" + emp + ", todoDate=" + todoDate + ", todoContent=" + todoContent
				+ ", completeYn=" + completeYn + ", todoDeadline=" + todoDeadline + "]";
	}

	@PrePersist
	public void preActive() {
		this.todoDate = this.todoDate == null ? new Date(System.currentTimeMillis()) : this.todoDate;
	}
}
