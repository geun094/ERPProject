package com.greedy.erp.production.production.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.greedy.erp.regist.entity.Product;

@Entity
@Table(name = "RECEIVINGDETAIL")
@SequenceGenerator(
		name = "RECEIVINGDETAIL_SEQ_GENERATOR",
		sequenceName = "SEQ_RECEIVINGDETAIL_CODE",
		initialValue = 1, allocationSize = 1
	)
public class ReceivingDetail {

	@Id
	@Column(name = "RECEIVING_NO")
	@GeneratedValue(
		strategy = GenerationType.SEQUENCE,
		generator = "RECEIVINGDETAIL_SEQ_GENERATOR"
	)
	private int receivingNo;
	
	@Column(name = "RECEIVING_AMOUNT")
	private int receivingAmount;
	
	@Column(name = "RECEIVING_NOTE")
	private String receivingNote;
	
	@ManyToOne
	@JoinColumn(name = "RECEIVING_CODE")
	private Receiving receiving;
	
	@ManyToOne
	@JoinColumn(name = "PRODUCT_CODE")
	private Product product;
	
	
	
	public ReceivingDetail() {
		super();
	}
	public ReceivingDetail(int receivingNo, int receivingAmount, String receivingNote, Receiving receiving,
			Product product) {
		super();
		this.receivingNo = receivingNo;
		this.receivingAmount = receivingAmount;
		this.receivingNote = receivingNote;
		this.receiving = receiving;
		this.product = product;
	}
	public int getReceivingNo() {
		return receivingNo;
	}
	public void setReceivingNo(int receivingNo) {
		this.receivingNo = receivingNo;
	}
	public int getReceivingAmount() {
		return receivingAmount;
	}
	public void setReceivingAmount(int receivingAmount) {
		this.receivingAmount = receivingAmount;
	}
	public String getReceivingNote() {
		return receivingNote;
	}
	public void setReceivingNote(String receivingNote) {
		this.receivingNote = receivingNote;
	}
	public Receiving getReceiving() {
		return receiving;
	}
	public void setReceiving(Receiving receiving) {
		this.receiving = receiving;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	@Override
	public String toString() {
		return "ReceivingDetail [receivingNo=" + receivingNo + ", receivingAmount=" + receivingAmount
				+ ", receivingNote=" + receivingNote + ", receiving=" + receiving + ", product=" + product + "]";
	}
	
	
}
