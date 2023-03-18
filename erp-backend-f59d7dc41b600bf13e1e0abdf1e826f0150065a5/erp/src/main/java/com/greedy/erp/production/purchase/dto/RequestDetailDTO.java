package com.greedy.erp.production.purchase.dto;

import com.greedy.erp.regist.dto.ProductDTO;

public class RequestDetailDTO {

	private int requestNo;
	private int requestAmount;
	private String requestNote;
	private int requestCode;
	private ProductDTO product;
	
	public RequestDetailDTO() {
		super();
	}
	
	public RequestDetailDTO(int requestNo, int requestAmount, String requestNote, int requestCode,
			ProductDTO product) {
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
	public ProductDTO getProduct() {
		return product;
	}
	public void setProduct(ProductDTO product) {
		this.product = product;
	}
	@Override
	public String toString() {
		return "RequestDetailDTO [requestNo=" + requestNo + ", requestAmount=" + requestAmount + ", requestNote="
				+ requestNote + ", requestCode=" + requestCode + ", product=" + product + "]";
	}
	
	
	
}
