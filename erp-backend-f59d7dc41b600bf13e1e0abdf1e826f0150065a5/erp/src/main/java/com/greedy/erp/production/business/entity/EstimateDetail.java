package com.greedy.erp.production.business.entity;

import javax.persistence.*;
import com.greedy.erp.regist.entity.Product;


@Entity
@Table(name = "ESTIMATEDETAIL")
@SequenceGenerator(
		name = "ESTIMATEDETAIL_SEQ_GENERATOR",
		sequenceName = "SEQ_ESTIMATE_NO",
		initialValue = 1, allocationSize = 1
		)
public class EstimateDetail {
	
	@Id
	@Column( name = "ESTIMATE_NO")
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "ESTIMATEDETAIL_SEQ_GENERATOR"
		)
	private int estimateNo;
	
	@Column( name = "ESTIMATE_AMOUNT")
	private int estimateAmount;
	
	@Column( name = "ESTIMATE_NOTE")
	private String estimateNote;
	
	@Column( name = "ESTIMATE_CODE", insertable = false, updatable = false)
	private int estimateCode;
	
	@ManyToOne (cascade = CascadeType.MERGE)
	@JoinColumn( name = "PRODUCT_CODE")
	private Product product;
	
	public EstimateDetail() {}

	public EstimateDetail(int estimateNo, int estimateAmount, String estimateNote, int estimateCode, Product product) {
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "EstimateDetail [estimateNo=" + estimateNo + ", estimateAmount=" + estimateAmount + ", estimateNote="
				+ estimateNote + ", estimateCode=" + estimateCode + ", product=" + product + "]";
	}

	
}