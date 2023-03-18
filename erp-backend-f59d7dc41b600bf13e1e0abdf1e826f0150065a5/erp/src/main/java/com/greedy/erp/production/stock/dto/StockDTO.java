package com.greedy.erp.production.stock.dto;

import com.greedy.erp.regist.dto.ProductDTO;
import com.greedy.erp.regist.dto.StorageDTO;

public class StockDTO {
	private int stockCode;
	private int stockAmount;
	private StorageDTO storage;
	private ProductDTO product;
	
	public StockDTO() {}

	public StockDTO(int stockCode, int stockAmount, StorageDTO storage, ProductDTO product) {
		super();
		this.stockCode = stockCode;
		this.stockAmount = stockAmount;
		this.storage = storage;
		this.product = product;
	}

	public int getStockCode() {
		return stockCode;
	}

	public void setStockCode(int stockCode) {
		this.stockCode = stockCode;
	}

	public int getStockAmount() {
		return stockAmount;
	}

	public void setStockAmount(int stockAmount) {
		this.stockAmount = stockAmount;
	}

	public StorageDTO getStorage() {
		return storage;
	}

	public void setStorage(StorageDTO storage) {
		this.storage = storage;
	}

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "StockDTO [stockCode=" + stockCode + ", stockAmount=" + stockAmount + ", storage=" + storage
				+ ", product=" + product + "]";
	}

}