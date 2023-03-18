package com.greedy.erp.production.stock.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.greedy.erp.regist.entity.Product;
import com.greedy.erp.regist.entity.Storage;

@Entity
@Table(name = "STOCK")
@SequenceGenerator(
		name = "STOCK_SEQ_GENERATOR",
		sequenceName = "SEQ_STOCK_CODE",
		initialValue = 1, allocationSize = 1
		)
public class Stock {
	
	@Id
	@Column(name = "STOCK_CODE")
	private int stockCode;
	
	@Column(name = "STOCK_AMOUNT")
	private int stockAmount;
	
	@ManyToOne
	@JoinColumn(name = "STORAGE_CODE")
	private Storage storage;
	
	@ManyToOne
	@JoinColumn(name = "PRODUCT_CODE")
	private Product product;
	
	public Stock() {}

	public Stock(int stockCode, int stockAmount, Storage storage, Product product) {
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

	public Storage getStorage() {
		return storage;
	}

	public void setStorage(Storage storage) {
		this.storage = storage;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Stock [stockCode=" + stockCode + ", stockAmount=" + stockAmount + ", storage=" + storage + ", product="
				+ product + "]";
	}
	
}