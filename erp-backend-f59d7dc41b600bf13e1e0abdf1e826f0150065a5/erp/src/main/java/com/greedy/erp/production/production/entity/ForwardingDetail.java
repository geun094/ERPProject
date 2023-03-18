package com.greedy.erp.production.production.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.greedy.erp.production.stock.entity.Stock;
import com.greedy.erp.regist.entity.Product;

/**
 * @author kingm
 *
 */
@Entity
@Table(name = "FORWARDINGDETAIL")
public class ForwardingDetail {
	
	@Id
	@Column(name = "FORWARDING_NO")
	private int forwardingNo;
	
	@Column(name = "FORWARDING_AMOUNT")
	private int forwardingAmount;
	
	@Column(name = "FORWARDING_CODE")
	private int forwardingCode;
	
	@Column(name = "FORWARDING_NOTE")
	private String forwardingNote;
	
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn( name = "STOCK_CODE")
	private Stock stock;
	
	public ForwardingDetail() {
		super();
	}

	public ForwardingDetail(int forwardingNo, int forwardingAmount, int forwardingCode, String forwardingNote,
			Stock stock) {
		super();
		this.forwardingNo = forwardingNo;
		this.forwardingAmount = forwardingAmount;
		this.forwardingCode = forwardingCode;
		this.forwardingNote = forwardingNote;
		this.stock = stock;
	}

	public int getForwardingNo() {
		return forwardingNo;
	}

	public void setForwardingNo(int forwardingNo) {
		this.forwardingNo = forwardingNo;
	}

	public int getForwardingAmount() {
		return forwardingAmount;
	}

	public void setForwardingAmount(int forwardingAmount) {
		this.forwardingAmount = forwardingAmount;
	}

	public int getForwardingCode() {
		return forwardingCode;
	}

	public void setForwardingCode(int forwardingCode) {
		this.forwardingCode = forwardingCode;
	}

	public String getForwardingNote() {
		return forwardingNote;
	}

	public void setForwardingNote(String forwardingNote) {
		this.forwardingNote = forwardingNote;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "ForwardingDetail [forwardingNo=" + forwardingNo + ", forwardingAmount=" + forwardingAmount
				+ ", forwardingCode=" + forwardingCode + ", forwardingNote=" + forwardingNote + ", stock=" + stock
				+ "]";
	}

	
	
	
	
}
