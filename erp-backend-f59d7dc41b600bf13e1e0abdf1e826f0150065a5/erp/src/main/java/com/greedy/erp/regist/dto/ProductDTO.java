package com.greedy.erp.regist.dto;

public class ProductDTO {

	private int productCode;
	private String productName;
	private int productRcvPrice;
	private int productFwdPriceA;
	private int productFwdPriceB;
	private int productFwdPriceC;
	private String productType;
	private String productImageUrl;
	
	public ProductDTO() {}

	public ProductDTO(int productCode, String productName, int productRcvPrice, int productFwdPriceA,
			int productFwdPriceB, int productFwdPriceC, String productType, String productImageUrl) {
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
		return "ProductDTO [productCode=" + productCode + ", productName=" + productName + ", productRcvPrice="
				+ productRcvPrice + ", productFwdPriceA=" + productFwdPriceA + ", productFwdPriceB=" + productFwdPriceB
				+ ", productFwdPriceC=" + productFwdPriceC + ", productType=" + productType + ", productImageUrl="
				+ productImageUrl + "]";
	}

}
