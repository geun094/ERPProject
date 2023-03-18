package com.greedy.erp.production.account.dto;

import java.util.Date;

import com.greedy.erp.production.business.dto.SalesDTO;

public class DepositDTO {
	private int depositCode;
	private Date depositDate;
	private int depositAmount;
	private int depositReceivable;
	private String depositNote;
	private SalesDTO sales;
	
	public DepositDTO() {}

	public DepositDTO(int depositCode, Date depositDate, int depositAmount, int depositReceivable, String depositNote,
			SalesDTO sales) {
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

	public SalesDTO getSales() {
		return sales;
	}

	public void setSales(SalesDTO sales) {
		this.sales = sales;
	}

	@Override
	public String toString() {
		return "DepositDTO [depositCode=" + depositCode + ", depositDate=" + depositDate + ", depositAmount="
				+ depositAmount + ", depositReceivable=" + depositReceivable + ", depositNote=" + depositNote
				+ ", sales=" + sales + "]";
	}
	
}