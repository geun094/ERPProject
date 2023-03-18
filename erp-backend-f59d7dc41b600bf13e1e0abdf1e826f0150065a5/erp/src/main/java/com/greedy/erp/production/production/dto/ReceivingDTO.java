package com.greedy.erp.production.production.dto;

import java.sql.Date;

import com.greedy.erp.regist.dto.ClientDTO;
import com.greedy.erp.regist.dto.EmpDTO;
import com.greedy.erp.regist.dto.ProductDTO;
import com.greedy.erp.regist.dto.StorageDTO;

public class ReceivingDTO {

	private int receivingCode;
	private Date receivingDate;
	private String receivingNote;
	private EmpDTO emp;
	private ProductDTO product;
	private StorageDTO storage;
	private ClientDTO client;
	
	public ReceivingDTO() {
		super();
	}

	public ReceivingDTO(int receivingCode, Date receivingDate, String receivingNote, EmpDTO emp, ProductDTO product,
			StorageDTO storage, ClientDTO client) {
		super();
		this.receivingCode = receivingCode;
		this.receivingDate = receivingDate;
		this.receivingNote = receivingNote;
		this.emp = emp;
		this.product = product;
		this.storage = storage;
		this.client = client;
	}

	public int getReceivingCode() {
		return receivingCode;
	}

	public void setReceivingCode(int receivingCode) {
		this.receivingCode = receivingCode;
	}

	public Date getReceivingDate() {
		return receivingDate;
	}

	public void setReceivingDate(Date receivingDate) {
		this.receivingDate = receivingDate;
	}

	public String getReceivingNote() {
		return receivingNote;
	}

	public void setReceivingNote(String receivingNote) {
		this.receivingNote = receivingNote;
	}

	public EmpDTO getEmp() {
		return emp;
	}

	public void setEmp(EmpDTO emp) {
		this.emp = emp;
	}

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}

	public StorageDTO getStorage() {
		return storage;
	}

	public void setStorage(StorageDTO storage) {
		this.storage = storage;
	}

	public ClientDTO getClient() {
		return client;
	}

	public void setClient(ClientDTO client) {
		this.client = client;
	}

	@Override
	public String toString() {
		return "ReceivingDTO [receivingCode=" + receivingCode + ", receivingDate=" + receivingDate + ", receivingNote="
				+ receivingNote + ", emp=" + emp + ", product=" + product + ", storage=" + storage + ", client="
				+ client + "]";
	}
	
	
	
}
