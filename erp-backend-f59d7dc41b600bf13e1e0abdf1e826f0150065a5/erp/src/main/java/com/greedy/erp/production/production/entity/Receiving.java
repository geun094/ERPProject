package com.greedy.erp.production.production.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.greedy.erp.regist.entity.Client;
import com.greedy.erp.regist.entity.Emp;
import com.greedy.erp.regist.entity.Product;
import com.greedy.erp.regist.entity.Storage;

@Entity
@Table(name = "RECEIVING")
@SequenceGenerator(
		name = "RECEIVING_SEQ_GENERATOR",
		sequenceName = "SEQ_RECEIVING_CODE",
		initialValue = 1, allocationSize = 1
	)
public class Receiving {

	@Id
	@Column(name = "RECEIVING_CODE")
	@GeneratedValue(
		strategy = GenerationType.SEQUENCE,
		generator = "RECEIVING_SEQ_GENERATOR"
	)
	private int receivingCode;
	
	@Column(name = "RECEIVING_DATE")
	private Date receivingDate;
	
	@Column(name = "RECEIVING_NOTE")
	private String receivingNote;
	
	@ManyToOne
	@JoinColumn(name = "EMP_CODE")
	private Emp emp;
	
	@ManyToOne
	@JoinColumn(name = "PRODUCT_CODE")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "STORAGE_CODE")
	private Storage storage;
	
	@ManyToOne
	@JoinColumn(name = "CLIENT_CODE")
	private Client client;
	
	
	public Receiving() {
		super();
	}
	public Receiving(int receivingCode, Date receivingDate, String receivingNote, Emp emp, Product product,
			Storage storage, Client client) {
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
	@Override
	public String toString() {
		return "Receiving [receivingCode=" + receivingCode + ", receivingDate=" + receivingDate + ", receivingNote="
				+ receivingNote + ", emp=" + emp + ", product=" + product + ", storage=" + storage + ", client="
				+ client + "]";
	}
	
	
}
