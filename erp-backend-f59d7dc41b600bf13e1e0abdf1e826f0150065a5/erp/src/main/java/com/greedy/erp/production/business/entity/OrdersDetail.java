package com.greedy.erp.production.business.entity;

import javax.persistence.*;
import com.greedy.erp.regist.entity.Product;


@Entity
@Table(name = "ORDERSDETAIL")
@SequenceGenerator(
		name = "ORDERSDETAIL_SEQ_GENERATOR",
		sequenceName = "SEQ_ORDERS_NO",
		initialValue = 1, allocationSize = 1
		)
public class OrdersDetail {
	
	@Id
	@Column( name = "ORDERS_NO")
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "ORDERSDETAIL_SEQ_GENERATOR"
		)
	private int ordersNo;
	
	@Column( name = "ORDERS_AMOUNT")
	private int ordersAmount;
	
	@Column( name = "ORDERS_NOTE")
	private String ordersNote;
	
	@Column( name = "ORDERS_CODE", insertable = false, updatable = false)
	private int ordersCode;
	
	@ManyToOne (cascade = CascadeType.MERGE)
	@JoinColumn( name = "PRODUCT_CODE")
	private Product product;
	
	public OrdersDetail() {}

	public OrdersDetail(int ordersNo, int ordersAmount, String ordersNote, int ordersCode, Product product) {
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "OrdersDetail [ordersNo=" + ordersNo + ", ordersAmount=" + ordersAmount + ", ordersNote="
				+ ordersNote + ", ordersCode=" + ordersCode + ", product=" + product + "]";
	}

	
}