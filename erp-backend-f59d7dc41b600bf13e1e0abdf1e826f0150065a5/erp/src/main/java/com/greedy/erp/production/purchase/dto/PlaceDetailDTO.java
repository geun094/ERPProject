package com.greedy.erp.production.purchase.dto;

import com.greedy.erp.regist.dto.ProductDTO;

public class PlaceDetailDTO {

	private int placeNo;
	private int placeAmount;
	private String placeNote;
	private	int placeCode;
	private ProductDTO product;
	
	public PlaceDetailDTO() {
		super();
	}
	
	public PlaceDetailDTO(int placeNo, int placeAmount, String placeNote, int placeCode, ProductDTO product) {
		super();
		this.placeNo = placeNo;
		this.placeAmount = placeAmount;
		this.placeNote = placeNote;
		this.placeCode = placeCode;
		this.product = product;
	}

	public int getPlaceNo() {
		return placeNo;
	}

	public void setPlaceNo(int placeNo) {
		this.placeNo = placeNo;
	}

	public int getPlaceAmount() {
		return placeAmount;
	}

	public void setPlaceAmount(int placeAmount) {
		this.placeAmount = placeAmount;
	}

	public String getPlaceNote() {
		return placeNote;
	}

	public void setPlaceNote(String placeNote) {
		this.placeNote = placeNote;
	}

	public int getPlaceCode() {
		return placeCode;
	}

	public void setPlaceCode(int placeCode) {
		this.placeCode = placeCode;
	}

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "PlaceDetailDTO [placeNo=" + placeNo + ", placeAmount=" + placeAmount + ", placeNote=" + placeNote
				+ ", placeCode=" + placeCode + ", product=" + product + "]";
	}
	
	
	
}
