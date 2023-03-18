package com.greedy.erp.production.production.dto;

import com.greedy.erp.regist.dto.ProductDTO;

public class ReceivingDetailDTO {

	private int receivingNo;
	private int receivingAmount;
	private String receivingNote;
	private ReceivingDTO receiving;
	private ProductDTO product;
	
	
	public ReceivingDetailDTO() {
		super();
	}
	public ReceivingDetailDTO(int receivingNo, int receivingAmount, String receivingNote, ReceivingDTO receiving,
			ProductDTO product) {
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
	public ReceivingDTO getReceiving() {
		return receiving;
	}
	public void setReceiving(ReceivingDTO receiving) {
		this.receiving = receiving;
	}
	public ProductDTO getProduct() {
		return product;
	}
	public void setProduct(ProductDTO product) {
		this.product = product;
	}
	@Override
	public String toString() {
		return "ReceivingDetailDTO [receivingNo=" + receivingNo + ", receivingAmount=" + receivingAmount
				+ ", receivingNote=" + receivingNote + ", receiving=" + receiving + ", product=" + product + "]";
	}
	
	
	
}
