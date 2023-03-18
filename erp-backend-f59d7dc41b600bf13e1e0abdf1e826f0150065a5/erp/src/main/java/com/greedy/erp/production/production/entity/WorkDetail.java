package com.greedy.erp.production.production.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.greedy.erp.production.stock.entity.Stock;
import com.greedy.erp.regist.entity.Product;
import com.greedy.erp.regist.entity.Storage;

@Entity
@Table(name = "WORKDETAIL")
public class WorkDetail {
	
	@Id
	@Column(name = "WORK_NO")
	private int workNo;
	
	@Column(name = "PRODUCTION_QUANTITY")
	private int productionQuantity;
	
	@Column(name = "CONSUMPTION_PER_INGREDIENT")
	private int consumptionPerIngredient;
	
	@Column(name = "WORK_NOTE")
	private String workNote;
	
	@Column(name = "WORK_CODE")
	private int workCode;
	
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn( name = "STOCK_CODE")
	private Stock stock;

	public WorkDetail() {
		super();
	}

	public WorkDetail(int workNo, int productionQuantity, int consumptionPerIngredient, String workNote, int workCode,
			int ingredientCode, int storageCode, Stock stock) {
		super();
		this.workNo = workNo;
		this.productionQuantity = productionQuantity;
		this.consumptionPerIngredient = consumptionPerIngredient;
		this.workNote = workNote;
		this.workCode = workCode;
		this.stock = stock;
	}

	public int getWorkNo() {
		return workNo;
	}

	public void setWorkNo(int workNo) {
		this.workNo = workNo;
	}

	public int getProductionQuantity() {
		return productionQuantity;
	}

	public void setProductionQuantity(int productionQuantity) {
		this.productionQuantity = productionQuantity;
	}

	public int getConsumptionPerIngredient() {
		return consumptionPerIngredient;
	}

	public void setConsumptionPerIngredient(int consumptionPerIngredient) {
		this.consumptionPerIngredient = consumptionPerIngredient;
	}

	public String getWorkNote() {
		return workNote;
	}

	public void setWorkNote(String workNote) {
		this.workNote = workNote;
	}

	public int getWorkCode() {
		return workCode;
	}

	public void setWorkCode(int workCode) {
		this.workCode = workCode;
	}



	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "WorkDetail [workNo=" + workNo + ", productionQuantity=" + productionQuantity
				+ ", consumptionPerIngredient=" + consumptionPerIngredient + ", workNote=" + workNote + ", workCode="
				+ workCode + ", stock=" + stock + "]";
	}

	

	
	
}
