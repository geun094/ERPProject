package com.greedy.erp.production.business.entity;

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
@Table(name = "SALESDETAIL")
@SequenceGenerator(
		name = "SALESDETAIL_SEQ_GENERATOR",
		sequenceName = "SEQ_SALES_NO",
		initialValue = 1, allocationSize = 1
		)
public class SalesDetail {
	
	@Id
	@Column(name = "SALES_NO")
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "SALESDETAIL_SEQ_GENERATOR"
		)
	private int salesNo;
	
	@Column(name = "SALES_AMOUNT")
	private int salesAmount;
	
	@Column(name = "SALES_NOTE")
	private String salesNote;
	
	@Column( name = "SALES_CODE", insertable = false, updatable = false)
	private int salesCode;
	
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn( name = "STOCK_CODE")
	private Stock stock;
	
	public SalesDetail() {}

	public SalesDetail(int salesNo, int salesAmount, String salesNote, int salesCode, Stock stock) {
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
		return "SalesDetail [salesNo=" + salesNo + ", salesAmount=" + salesAmount + ", salesNote=" + salesNote
				+ ", salesCode=" + salesCode + ", stock=" + stock + "]";
	}

}