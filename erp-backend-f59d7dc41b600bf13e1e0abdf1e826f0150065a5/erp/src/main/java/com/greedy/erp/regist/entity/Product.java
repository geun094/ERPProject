package com.greedy.erp.regist.entity;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT")
@SequenceGenerator(
		name = "PRODUCT_SEQ_GENERATOR",
		sequenceName = "SEQ_PRODUCT_CODE",
		initialValue = 1, allocationSize = 1
		)
public class Product {

	@Id
	@Column(name = "PRODUCT_CODE")
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "PRODUCT_SEQ_GENERATOR"
		)
	private int productCode;
	
	@Column(name = "PRODUCT_NAME")
	private String productName;
	
	@Column(name = "PRODUCT_RCVPRICE")
	private int productRcvPrice;
	
	@Column(name = "PRODUCT_FWDPRICEA")
	private int productFwdPriceA;
	
	@Column(name = "PRODUCT_FWDPRICEB")
	private int productFwdPriceB;
	
	@Column(name = "PRODUCT_FWDPRICEC")
	private int productFwdPriceC;
	
	@Column(name = "PRODUCT_TYPE")
	private String productType;
	
	@Column(name = "PRODUCT_IMAGE")
	private String productImageUrl;

	public Product() {
	}

	public Product(int productCode, String productName, int productRcvPrice, int productFwdPriceA, int productFwdPriceB,
			int productFwdPriceC, String productType, String productImageUrl) {
		this.productCode = productCode;
		this.productName = productName;
		this.productRcvPrice = productRcvPrice;
		this.productFwdPriceA = productFwdPriceA;
		this.productFwdPriceB = productFwdPriceB;
		this.productFwdPriceC = productFwdPriceC;
		this.productType = productType;
		this.productImageUrl = productImageUrl;
	}

	public int getProductCode() {
		return productCode;
	}

	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductRcvPrice() {
		return productRcvPrice;
	}

	public void setProductRcvPrice(int productRcvPrice) {
		this.productRcvPrice = productRcvPrice;
	}

	public int getProductFwdPriceA() {
		return productFwdPriceA;
	}

	public void setProductFwdPriceA(int productFwdPriceA) {
		this.productFwdPriceA = productFwdPriceA;
	}

	public int getProductFwdPriceB() {
		return productFwdPriceB;
	}

	public void setProductFwdPriceB(int productFwdPriceB) {
		this.productFwdPriceB = productFwdPriceB;
	}

	public int getProductFwdPriceC() {
		return productFwdPriceC;
	}

	public void setProductFwdPriceC(int productFwdPriceC) {
		this.productFwdPriceC = productFwdPriceC;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductImageUrl() {
		return productImageUrl;
	}

	public void setProductImageUrl(String productImageUrl) {
		this.productImageUrl = productImageUrl;
	}

	@Override
	public String toString() {
		return "Product [productCode=" + productCode + ", productName=" + productName + ", productRcvPrice="
				+ productRcvPrice + ", productFwdPriceA=" + productFwdPriceA + ", productFwdPriceB=" + productFwdPriceB
				+ ", productFwdPriceC=" + productFwdPriceC + ", productType=" + productType + ", productImageUrl="
				+ productImageUrl + "]";
	}
	
	
	
}
