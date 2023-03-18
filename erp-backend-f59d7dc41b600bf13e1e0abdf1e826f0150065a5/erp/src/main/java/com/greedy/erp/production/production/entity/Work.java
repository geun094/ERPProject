package com.greedy.erp.production.production.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.greedy.erp.authority.entity.Authority;
import com.greedy.erp.regist.entity.Client;
import com.greedy.erp.regist.entity.Emp;
import com.greedy.erp.regist.entity.Product;
import com.greedy.erp.regist.entity.Storage;


@Entity
@Table(name = "WORK")
@SequenceGenerator(
		name = "WORK_SEQ_GENERATOR",
		sequenceName = "SEQ_WORK_CODE",
		initialValue = 1, allocationSize = 1
		)
public class Work {
	
	@Id
	@Column(name = "WORK_CODE")
	private int workCode;
	
	@Column(name = "WORK_DATE")
	private Date workDate;
	
	@Column(name = "WORK_NOTE")
	private String workNote;
	
	@Column(name = "PRODUCT_AMOUNT")
	private int productAmount;
	
	
	@Column(name = "EMP_CODE")
	private int empCode;
	
	@Column(name = "PRODUCT_CODE")
	private int productCode;
	
	@Column(name = "STORAGE_CODE")
	private int storageCode;
	
	@Column(name = "CLIENT_CODE")
	private int clientCode;
	
	@ManyToOne
	@JoinColumn(name = "EMP_CODE", insertable = false, updatable = false)
	private Emp emp;
	
	@ManyToOne
	@JoinColumn(name = "PRODUCT_CODE", insertable = false, updatable = false)
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "STORAGE_CODE", insertable = false, updatable = false)
	private Storage storage;
	
	@ManyToOne
	@JoinColumn(name = "CLIENT_CODE", insertable = false, updatable = false)
	private Client client;
	
	@OneToMany
	@JoinColumn(name = "WORK_CODE")
	private List<WorkDetail> workDetailList;
	

	
	public Work() {
		super();
	}



	public Work(int workCode, Date workDate, String workNote, int productAmount, int empCode, int productCode,
			int storageCode, int clientCode, Emp emp, Product product, Storage storage, Client client,
			List<WorkDetail> workDetailList) {
		super();
		this.workCode = workCode;
		this.workDate = workDate;
		this.workNote = workNote;
		this.productAmount = productAmount;
		this.empCode = empCode;
		this.productCode = productCode;
		this.storageCode = storageCode;
		this.clientCode = clientCode;
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



	public int getProductAmount() {
		return productAmount;
	}



	public void setProductAmount(int productAmount) {
		this.productAmount = productAmount;
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
		return "Work [workCode=" + workCode + ", workDate=" + workDate + ", workNote=" + workNote + ", productAmount="
				+ productAmount + ", empCode=" + empCode + ", productCode=" + productCode + ", storageCode="
				+ storageCode + ", clientCode=" + clientCode + ", emp=" + emp + ", product=" + product + ", storage="
				+ storage + ", client=" + client + ", workDetailList=" + workDetailList + "]";
	}




	
}
