package com.greedy.erp.production.purchase.entity;


import javax.persistence.*;

import com.greedy.erp.regist.entity.Product;


@Entity
@Table(name = "PLACEDETAIL")
@SequenceGenerator(
		name = "PLACEDETAIL_SEQ_GENERATOR",
		sequenceName = "SEQ_PLACEDETAIL_CODE",
		initialValue = 1, allocationSize = 1
		)

public class PlaceDetail {
	
	@Id
	@Column( name = "PLACE_NO")
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "PLACEDETAIL_SEQ_GENERATOR"
		)
	private int placeNo;
	
	@Column(name = "PLACE_AMOUNT")
	private int placeAmount;
	
	@Column(name = "PLACE_NOTE")
	private String placeNote;
	
	@Column(name = "PLACE_CODE")
	private int placeCode;
	
	@ManyToOne
	@JoinColumn(name = "PRODUCT_CODE")
	private Product product;

	public PlaceDetail() {}
	
	public PlaceDetail(int placeNo, int placeAmount, String placeNote, int placeCode, Product product) {
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "PlaceDetail [placeNo=" + placeNo + ", placeAmount=" + placeAmount + ", placeNote=" + placeNote
				+ ", placeCode=" + placeCode + ", product=" + product + "]";
	} 

	
	
}
