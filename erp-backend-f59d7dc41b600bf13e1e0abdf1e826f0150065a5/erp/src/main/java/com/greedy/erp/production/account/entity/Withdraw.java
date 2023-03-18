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

import com.greedy.erp.production.purchase.entity.Purchase;

@Entity
@Table(name = "WITHDRAW")
@SequenceGenerator(
		name = "WITHDRAW_SEQ_GENERATOR",
		sequenceName = "SEQ_WITHDRAW_CODE",
		initialValue = 1, allocationSize = 1
		)
public class Withdraw {
	
	@Id
	@Column( name = "WITHDRAW_CODE")
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "WITHDRAW_SEQ_GENERATOR"
		)
	private int withdrawCode;
	
	@Column( name = "WITHDRAW_DATE")
	private Date withdrawDate;
	
	@Column( name = "WITHDRAW_AMOUNT")
	private int withdrawAmount;
	
	@Column( name = "WITHDRAW_PAYABLE")
	private int withdrawPayable;
	
	@Column( name = "WITHDRAW_NOTE")
	private String withdrawNote;
	
	@ManyToOne
	@JoinColumn( name = "PURCHASE_CODE")
	private Purchase purchase;
	
	public Withdraw() {}

	public Withdraw(int withdrawCode, Date withdrawDate, int withdrawAmount, int withdrawPayable, String withdrawNote,
			Purchase purchase) {
		super();
		this.withdrawCode = withdrawCode;
		this.withdrawDate = withdrawDate;
		this.withdrawAmount = withdrawAmount;
		this.withdrawPayable = withdrawPayable;
		this.withdrawNote = withdrawNote;
		this.purchase = purchase;
	}

	public int getWithdrawCode() {
		return withdrawCode;
	}

	public void setWithdrawCode(int withdrawCode) {
		this.withdrawCode = withdrawCode;
	}

	public Date getWithdrawDate() {
		return withdrawDate;
	}

	public void setWithdrawDate(Date withdrawDate) {
		this.withdrawDate = withdrawDate;
	}

	public int getWithdrawAmount() {
		return withdrawAmount;
	}

	public void setWithdrawAmount(int withdrawAmount) {
		this.withdrawAmount = withdrawAmount;
	}

	public int getWithdrawPayable() {
		return withdrawPayable;
	}

	public void setWithdrawPayable(int withdrawPayable) {
		this.withdrawPayable = withdrawPayable;
	}

	public String getWithdrawNote() {
		return withdrawNote;
	}

	public void setWithdrawNote(String withdrawNote) {
		this.withdrawNote = withdrawNote;
	}

	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}

	@Override
	public String toString() {
		return "Withdraw [withdrawCode=" + withdrawCode + ", withdrawDate=" + withdrawDate + ", withdrawAmount="
				+ withdrawAmount + ", withdrawPayable=" + withdrawPayable + ", withdrawNote=" + withdrawNote
				+ ", purchase=" + purchase + "]";
	}
	
}