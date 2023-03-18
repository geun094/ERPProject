package com.greedy.erp.production.purchase.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.greedy.erp.regist.entity.Client;
import com.greedy.erp.regist.entity.Emp;
import com.greedy.erp.regist.entity.Storage;

@Entity
@Table(name = "PURCHASE")
@SequenceGenerator(
		name = "PURCHASE_SEQ_GENERATOR",
		sequenceName = "SEQ_PURCHASE_CODE",
		initialValue = 1, allocationSize = 1
		)

public class Purchase {
	
	@Id
	@Column(name = "PURCHASE_CODE")
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "PURCHASE_SEQ_GENERATOR"
		)
	private int purchaseCode;
	
	@Column(name = "PURCHASE_DATE")
	private Date purchaseDate;

	@ManyToOne
	@JoinColumn(name = "CLIENT_CODE")
	private Client client;
	
	@ManyToOne
	@JoinColumn(name = "EMP_CODE")
	private Emp emp;
			
	@OneToMany (cascade = CascadeType.ALL)
	@JoinColumn( name = "PURCHASE_CODE")
	private List<PurchaseDetail> purchaseDetail;
	
	public Purchase() {}

	public Purchase(int purchaseCode, Date purchaseDate, Client client, Emp emp, List<PurchaseDetail> purchaseDetail) {
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

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Emp getEmp() {
		return emp;
	}

	public void setEmp(Emp emp) {
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
		return "Purchase [purchaseCode=" + purchaseCode + ", purchaseDate=" + purchaseDate + ", client=" + client
				+ ", emp=" + emp + ", purchaseDetail=" + purchaseDetail + "]";
	}
	
	
	
	
	
}