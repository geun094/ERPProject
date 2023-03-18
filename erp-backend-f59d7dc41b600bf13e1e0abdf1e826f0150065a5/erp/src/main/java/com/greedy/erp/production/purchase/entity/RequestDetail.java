package com.greedy.erp.production.purchase.entity;

import javax.persistence.*;

import com.greedy.erp.regist.entity.Product;



@Entity
@Table(name = "REQUESTDETAIL")
@SequenceGenerator(
		name = "REQUESTDETAIL_SEQ_GENERATOR",
		sequenceName = "SEQ_REQUESTDETAIL_CODE",
		initialValue = 1, allocationSize = 1
		)

public class RequestDetail {
	@Id
	@Column( name = "REQUEST_NO")
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "REQUSETDETAIL_SEQ_GENERATOR"
		)
	private int requestNo;
	
	@Column(name = "REQUEST_AMOUNT")
	private int requestAmount;
	
	@Column(name = "REQUEST_NOTE")
	private String requestNote;
	
	@Column(name = "REQUEST_CODE")
	private int requestCode;
			
	@ManyToOne
	@JoinColumn(name = "PRODUCT_CODE")
	private Product product;

	public RequestDetail() {}
	
	public RequestDetail(int requestNo, int requestAmount, String requestNote, int requestCode, Product product) {
		super();
		this.requestNo = requestNo;
		this.requestAmount = requestAmount;
		this.requestNote = requestNote;
		this.requestCode = requestCode;
		this.product = product;
	}

	public int getRequestNo() {
		return requestNo;
	}

	public void setRequestNo(int requestNo) {
		this.requestNo = requestNo;
	}

	public int getRequestAmount() {
		return requestAmount;
	}

	public void setRequestAmount(int requestAmount) {
		this.requestAmount = requestAmount;
	}

	public String getRequestNote() {
		return requestNote;
	}

	public void setRequestNote(String requestNote) {
		this.requestNote = requestNote;
	}

	public int getRequestCode() {
		return requestCode;
	}

	public void setRequestCode(int requestCode) {
		this.requestCode = requestCode;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "RequestDetail [requestNo=" + requestNo + ", requestAmount=" + requestAmount + ", requestNote="
				+ requestNote + ", requestCode=" + requestCode + ", product=" + product + "]";
	}
	
	
	
}