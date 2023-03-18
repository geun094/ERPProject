package com.greedy.erp.production.business.dto;

import com.greedy.erp.regist.dto.ProductDTO;

public class OrdersDetailDTO {
	private int ordersNo;
	private int ordersAmount;
	private String ordersNote;
	
	private int ordersCode;
	private ProductDTO product;
	
	public OrdersDetailDTO() {}

	public OrdersDetailDTO(int ordersNo, int ordersAmount, String ordersNote, int ordersCode,
			ProductDTO product) {
		super();
		this.ordersNo = ordersNo;
		this.ordersAmount = ordersAmount;
		this.ordersNote = ordersNote;
		this.ordersCode = ordersCode;
		this.product = product;
	}

	public int getOrdersNo() {
		return ordersNo;
	}

	public void setOrdersNo(int ordersNo) {
		this.ordersNo = ordersNo;
	}

	public int getOrdersAmount() {
		return ordersAmount;
	}

	public void setOrdersAmount(int ordersAmount) {
		this.ordersAmount = ordersAmount;
	}

	public String getOrdersNote() {
		return ordersNote;
	}

	public void setOrdersNote(String ordersNote) {
		this.ordersNote = ordersNote;
	}

	public int getOrdersCode() {
		return ordersCode;
	}

	public void setOrdersCode(int ordersCode) {
		this.ordersCode = ordersCode;
	}

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "OrdersDetailDTO [ordersNo=" + ordersNo + ", ordersAmount=" + ordersAmount + ", ordersNote="
				+ ordersNote + ", ordersCode=" + ordersCode + ", product=" + product + "]";
	}

}