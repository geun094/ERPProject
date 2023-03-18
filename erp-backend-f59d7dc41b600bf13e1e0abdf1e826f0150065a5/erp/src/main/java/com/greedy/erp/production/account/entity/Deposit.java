package com.greedy.erp.production.account.entity;

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

import com.greedy.erp.production.business.entity.Sales;

@Entity
@Table(name = "DEPOSIT")
@SequenceGenerator(
		name = "DEPOSIT_SEQ_GENERATOR",
		sequenceName = "SEQ_DEPOSIT_CODE",
		initialValue = 1, allocationSize = 1
		)
public class Deposit {
	
	@Id
	@Column( name = "DEPOSIT_CODE")
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "DEPOSIT_SEQ_GENERATOR"
		)
	private int depositCode;
	
	@Column( name = "DEPOSIT_DATE")
	private Date depositDate;
	
	@Column( name = "DEPOSIT_AMOUNT")
	private int depositAmount;
	
	@Column( name = "DEPOSIT_RECEIVABLE")
	private int depositReceivable;
	
	@Column( name = "DEPOSIT_NOTE")
	private String depositNote;
	
	@ManyToOne
	@JoinColumn( name = "SALES_CODE")
	private Sales sales;
	
	public Deposit() {
		super();
	}

	public Deposit(int depositCode, Date depositDate, int depositAmount, int depositReceivable, String depositNote,
			Sales sales) {
		super();
		this.depositCode = depositCode;
		this.depositDate = depositDate;
		this.depositAmount = depositAmount;
		this.depositReceivable = depositReceivable;
		this.depositNote = depositNote;
		this.sales = sales;
	}

	public int getDepositCode() {
		return depositCode;
	}

	public void setDepositCode(int depositCode) {
		this.depositCode = depositCode;
	}

	public Date getDepositDate() {
		return depositDate;
	}

	public void setDepositDate(Date depositDate) {
		this.depositDate = depositDate;
	}

	public int getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(int depositAmount) {
		this.depositAmount = depositAmount;
	}

	public int getDepositReceivable() {
		return depositReceivable;
	}

	public void setDepositReceivable(int depositReceivable) {
		this.depositReceivable = depositReceivable;
	}

	public String getDepositNote() {
		return depositNote;
	}

	public void setDepositNote(String depositNote) {
		this.depositNote = depositNote;
	}

	public Sales getSales() {
		return sales;
	}

	public void setSales(Sales sales) {
		this.sales = sales;
	}

	@Override
	public String toString() {
		return "Deposit [depositCode=" + depositCode + ", depositDate=" + depositDate + ", depositAmount="
				+ depositAmount + ", depositReceivable=" + depositReceivable + ", depositNote=" + depositNote
				+ ", sales=" + sales + "]";
	}

}