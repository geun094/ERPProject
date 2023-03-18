package com.greedy.erp.production.business.dto;

import com.greedy.erp.production.stock.entity.Stock;
import com.greedy.erp.regist.entity.Product;

public class SalesDetailDTO {
	private int salesNo;
	private int salesAmount;
	private String salesNote;
	
	private int salesCode;
	private Stock stock;
		
	public SalesDetailDTO() {}

	public SalesDetailDTO(int salesNo, int salesAmount, String salesNote, int salesCode, Stock stock) {
		super();
		this.salesNo = salesNo;
		this.salesAmount = salesAmount;
		this.salesNote = salesNote;
		this.salesCode = salesCode;
		this.stock = stock;
	}

	public int getSalesNo() {
		return salesNo;
	}

	public void setSalesNo(int salesNo) {
		this.salesNo = salesNo;
	}

	public int getSalesAmount() {
		return salesAmount;
	}

	public void setSalesAmount(int salesAmount) {
		this.salesAmount = salesAmount;
	}

	public String getSalesNote() {
		return salesNote;
	}

	public void setSalesNote(String salesNote) {
		this.salesNote = salesNote;
	}

	public int getSalesCode() {
		return salesCode;
	}

	public void setSalesCode(int salesCode) {
		this.salesCode = salesCode;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "SalesDetailDTO [salesNo=" + salesNo + ", salesAmount=" + salesAmount + ", salesNote=" + salesNote
				+ ", salesCode=" + salesCode + ", stock=" + stock + "]";
	}

}