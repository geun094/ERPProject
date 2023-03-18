package com.greedy.erp.production.account.dto;

import java.util.Date;

import com.greedy.erp.production.purchase.dto.PurchaseDTO;

public class WithdrawDTO {
	private int WithdrawCode;
	private Date WithdrawDate;
	private int WithdrawAmount;
	private int WithdrawPayable;
	private String WithdrawNote;
	private PurchaseDTO Purchase;
	
	public WithdrawDTO() {}

	public WithdrawDTO(int withdrawCode, Date withdrawDate, int withdrawAmount, int withdrawPayable,
			String withdrawNote, PurchaseDTO purchase) {
		super();
		WithdrawCode = withdrawCode;
		WithdrawDate = withdrawDate;
		WithdrawAmount = withdrawAmount;
		WithdrawPayable = withdrawPayable;
		WithdrawNote = withdrawNote;
		Purchase = purchase;
	}

	public int getWithdrawCode() {
		return WithdrawCode;
	}


	public void setWithdrawCode(int withdrawCode) {
		WithdrawCode = withdrawCode;
	}


	public Date getWithdrawDate() {
		return WithdrawDate;
	}


	public void setWithdrawDate(Date withdrawDate) {
		WithdrawDate = withdrawDate;
	}


	public int getWithdrawAmount() {
		return WithdrawAmount;
	}


	public void setWithdrawAmount(int withdrawAmount) {
		WithdrawAmount = withdrawAmount;
	}


	public int getWithdrawPayable() {
		return WithdrawPayable;
	}


	public void setWithdrawPayable(int withdrawPayable) {
		WithdrawPayable = withdrawPayable;
	}


	public String getWithdrawNote() {
		return WithdrawNote;
	}


	public void setWithdrawNote(String withdrawNote) {
		WithdrawNote = withdrawNote;
	}


	public PurchaseDTO getPurchase() {
		return Purchase;
	}


	public void setPurchase(PurchaseDTO purchase) {
		Purchase = purchase;
	}


	@Override
	public String toString() {
		return "WithdrawDTO [WithdrawCode=" + WithdrawCode + ", WithdrawDate=" + WithdrawDate + ", WithdrawAmount="
				+ WithdrawAmount + ", WithdrawPayable=" + WithdrawPayable + ", WithdrawNote=" + WithdrawNote
				+ ", Purchase=" + Purchase + "]";
	}

}