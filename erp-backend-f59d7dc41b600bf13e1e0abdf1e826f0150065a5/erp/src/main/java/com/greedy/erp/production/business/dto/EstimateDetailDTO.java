package com.greedy.erp.production.business.dto;

import com.greedy.erp.regist.dto.ProductDTO;

public class EstimateDetailDTO {
	private int estimateNo;
	private int estimateAmount;
	private String estimateNote;
	
	private int estimateCode;
	private ProductDTO product;
	
	public EstimateDetailDTO() {}

	public EstimateDetailDTO(int estimateNo, int estimateAmount, String estimateNote, int estimateCode,
			ProductDTO product) {
		super();
		this.estimateNo = estimateNo;
		this.estimateAmount = estimateAmount;
		this.estimateNote = estimateNote;
		this.estimateCode = estimateCode;
		this.product = product;
	}

	public int getEstimateNo() {
		return estimateNo;
	}

	public void setEstimateNo(int estimateNo) {
		this.estimateNo = estimateNo;
	}

	public int getEstimateAmount() {
		return estimateAmount;
	}

	public void setEstimateAmount(int estimateAmount) {
		this.estimateAmount = estimateAmount;
	}

	public String getEstimateNote() {
		return estimateNote;
	}

	public void setEstimateNote(String estimateNote) {
		this.estimateNote = estimateNote;
	}

	public int getEstimateCode() {
		return estimateCode;
	}

	public void setEstimateCode(int estimateCode) {
		this.estimateCode = estimateCode;
	}

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "EstimateDetailDTO [estimateNo=" + estimateNo + ", estimateAmount=" + estimateAmount + ", estimateNote="
				+ estimateNote + ", estimateCode=" + estimateCode + ", product=" + product + "]";
	}

}