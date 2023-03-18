package com.greedy.erp.production.purchase.dto;

import com.greedy.erp.production.stock.entity.Stock;

public class PurchaseDetailDTO {

	private int purchaseNo;
	private int purchaseAmount;
	private String purchaseNote;
	private int purchaseCode;
	private Stock stock;
	
	public PurchaseDetailDTO() {
		super();
	}
	
	public PurchaseDetailDTO(int purchaseNo, int purchaseAmount, String purchaseNote, int purchaseCode,
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
		return "PurchaseDetailDTO [purchaseNo=" + purchaseNo + ", purchaseAmount=" + purchaseAmount + ", purchaseNote="
				+ purchaseNote + ", purchaseCode=" + purchaseCode + ", stock=" + stock + "]";
	}
	
	
}
