package com.greedy.erp.production.purchase.entity;

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

import com.greedy.erp.production.stock.entity.Stock;
import com.greedy.erp.regist.entity.Product;

@Entity
@Table(name = "PURCHASEDETAIL")
@SequenceGenerator(
		name = "PURCHASEDETAIL_SEQ_GENERATOR",
		sequenceName = "SEQ_PURCHASE_NO",
		initialValue = 1, allocationSize = 1
		)

public class PurchaseDetail {
	
	@Id
	@Column(name = "PURCHASE_NO")
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "PURCHASEDETAIL_SEQ_GENERATOR"
		)
	private int purchaseNo;
	
	@Column(name = "PURCHASE_AMOUNT")
	private int purchaseAmount;
	
	@Column(name = "PURCHASE_NOTE")
	private String purchaseNote;	
	
	@Column( name = "PURCHASE_CODE", insertable = false, updatable = false)
	private int purchaseCode;
			
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn( name = "STOCK_CODE")
	private Stock stock;

	public PurchaseDetail() {}
	
	public PurchaseDetail(int purchaseNo, int purchaseAmount, String purchaseNote, int purchaseCode,
			Stock stock) {
		super();
		this.purchaseNo = purchaseNo;
		this.purchaseAmount = purchaseAmount;
		this.purchaseNote = purchaseNote;
		this.purchaseCode = purchaseCode;
		this.stock = stock;
	}

	public int getPurchaseNo() {
		return purchaseNo;
	}

	public void setPurchaseNo(int purchaseNo) {
		this.purchaseNo = purchaseNo;
	}

	public int getPurchaseAmount() {
		return purchaseAmount;
	}

	public void setPurchaseAmount(int purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}

	public String getPurchaseNote() {
		return purchaseNote;
	}

	public void setPurchaseNote(String purchaseNote) {
		this.purchaseNote = purchaseNote;
	}

	public int getPurchaseCode() {
		return purchaseCode;
	}

	public void setPurchaseCode(int purchaseCode) {
		this.purchaseCode = purchaseCode;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}
	@Override
	public String toString() {
		return "PurchaseDetail [purchaseNo=" + purchaseNo + ", purchaseAmount=" + purchaseAmount + ", purchaseNote="
				+ purchaseNote + ", purchaseCode=" + purchaseCode + ", stock=" + stock + "]";
	}
	
	
	
}