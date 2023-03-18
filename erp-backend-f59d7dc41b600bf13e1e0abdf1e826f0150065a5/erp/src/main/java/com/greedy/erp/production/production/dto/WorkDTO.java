package com.greedy.erp.production.production.dto;

import java.sql.Date;
import java.util.List;

import com.greedy.erp.production.production.entity.WorkDetail;
import com.greedy.erp.regist.entity.Client;
import com.greedy.erp.regist.entity.Emp;
import com.greedy.erp.regist.entity.Product;
import com.greedy.erp.regist.entity.Storage;

public class WorkDTO {
	
	private int workCode;
	private Date workDate;
	private String workNote;
	
	private int empCode;
	private int productCode;
	private int storageCode;
	private int clientCode;
	private int productAmount;
	
	private Emp emp;
	private Product product;
	private Storage storage;
	private Client client;
	
	private List<WorkDetail> workDetailList;

	public WorkDTO() {
		super();
	}

	public WorkDTO(int workCode, Date workDate, String workNote, int empCode, int productCode, int storageCode,
			int clientCode, int productAmount, Emp emp, Product product, Storage storage, Client client,
			List<WorkDetail> workDetailList) {
		super();
		this.workCode = workCode;
		this.workDate = workDate;
		this.workNote = workNote;
		this.empCode = empCode;
		this.productCode = productCode;
		this.storageCode = storageCode;
		this.clientCode = clientCode;
		this.productAmount = productAmount;
		this.emp = emp;
		this.product = product;
		this.storage = storage;
		this.client = client;
		this.workDetailList = workDetailList;
	}

	public int getWorkCode() {
		return workCode;
	}

	public void setWorkCode(int workCode) {
		this.workCode = workCode;
	}

	public Date getWorkDate() {
		return workDate;
	}

	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}

	public String getWorkNote() {
		return workNote;
	}

	public void setWorkNote(String workNote) {
		this.workNote = workNote;
	}

	public int getEmpCode() {
		return empCode;
	}

	public void setEmpCode(int empCode) {
		this.empCode = empCode;
	}

	public int getProductCode() {
		return productCode;
	}

	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}

	public int getStorageCode() {
		return storageCode;
	}

	public void setStorageCode(int storageCode) {
		this.storageCode = storageCode;
	}

	public int getClientCode() {
		return clientCode;
	}

	public void setClientCode(int clientCode) {
		this.clientCode = clientCode;
	}

	public int getProductAmount() {
		return productAmount;
	}

	public void setProductAmount(int productAmount) {
		this.productAmount = productAmount;
	}

	public Emp getEmp() {
		return emp;
	}

	public void setEmp(Emp emp) {
		this.emp = emp;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Storage getStorage() {
		return storage;
	}

	public void setStorage(Storage storage) {
		this.storage = storage;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<WorkDetail> getWorkDetailList() {
		return workDetailList;
	}

	public void setWorkDetailList(List<WorkDetail> workDetailList) {
		this.workDetailList = workDetailList;
	}

	@Override
	public String toString() {
		return "WorkDTO [workCode=" + workCode + ", workDate=" + workDate + ", workNote=" + workNote + ", empCode="
				+ empCode + ", productCode=" + productCode + ", storageCode=" + storageCode + ", clientCode="
				+ clientCode + ", productAmount=" + productAmount + ", emp=" + emp + ", product=" + product
				+ ", storage=" + storage + ", client=" + client + ", workDetailList=" + workDetailList + "]";
	}

	

}
