package com.greedy.erp.production.purchase.dto;

import java.util.Date;
import java.util.List;

import com.greedy.erp.production.purchase.entity.PurchaseDetail;
import com.greedy.erp.regist.dto.ClientDTO;
import com.greedy.erp.regist.dto.EmpDTO;
import com.greedy.erp.regist.dto.StorageDTO;


public class PurchaseDTO {

	private	int purchaseCode;
	private Date purchaseDate;
	
	private ClientDTO client;
	private EmpDTO emp;
	private List<PurchaseDetail> purchaseDetail;
	
	private PurchaseDTO() {
		super();
	}

	public PurchaseDTO(int purchaseCode, Date purchaseDate, ClientDTO client, EmpDTO emp,
			List<PurchaseDetail> purchaseDetail) {
		super();
		this.purchaseCode = purchaseCode;
		this.purchaseDate = purchaseDate;
		this.client = client;
		this.emp = emp;
		this.purchaseDetail = purchaseDetail;
	}

	public int getPurchaseCode() {
		return purchaseCode;
	}

	public void setPurchaseCode(int purchaseCode) {
		this.purchaseCode = purchaseCode;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public ClientDTO getClient() {
		return client;
	}

	public void setClient(ClientDTO client) {
		this.client = client;
	}

	public EmpDTO getEmp() {
		return emp;
	}

	public void setEmp(EmpDTO emp) {
		this.emp = emp;
	}

	public List<PurchaseDetail> getPurchaseDetail() {
		return purchaseDetail;
	}

	public void setPurchaseDetail(List<PurchaseDetail> purchaseDetail) {
		this.purchaseDetail = purchaseDetail;
	}

	@Override
	public String toString() {
		return "PurchaseDTO [purchaseCode=" + purchaseCode + ", purchaseDate=" + purchaseDate + ", client=" + client
				+ ", emp=" + emp + ", purchaseDetail=" + purchaseDetail + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
