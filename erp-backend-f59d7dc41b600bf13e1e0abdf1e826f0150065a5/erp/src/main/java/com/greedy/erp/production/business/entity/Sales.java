package com.greedy.erp.production.business.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.greedy.erp.regist.entity.Client;
import com.greedy.erp.regist.entity.Emp;
import com.greedy.erp.regist.entity.Storage;

@Entity
@Table(name = "SALES")
@SequenceGenerator(
		name = "SALES_SEQ_GENERATOR",
		sequenceName = "SEQ_SALES_CODE",
		initialValue = 1, allocationSize = 1
		)
public class Sales {
	
	@Id
	@Column(name = "SALES_CODE")
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "SALES_SEQ_GENERATOR"
		)
	private int salesCode;
	
	@Column(name = "SALES_DATE")
	private Date salesDate;
	
	@ManyToOne
	@JoinColumn(name = "CLIENT_CODE")
	private Client client;
	
	@ManyToOne
	@JoinColumn(name = "EMP_CODE")	
	private Emp emp;
	
	@OneToMany (cascade = CascadeType.ALL)
	@JoinColumn( name = "SALES_CODE")
	private List<SalesDetail> salesDetail;
	
	public Sales() {}

	public Sales(int salesCode, Date salesDate, Client client, Emp emp, List<SalesDetail> salesDetail) {
		super();
		this.salesCode = salesCode;
		this.salesDate = salesDate;
		this.client = client;
		this.emp = emp;
		this.salesDetail = salesDetail;
	}

	public int getSalesCode() {
		return salesCode;
	}

	public void setSalesCode(int salesCode) {
		this.salesCode = salesCode;
	}

	public Date getSalesDate() {
		return salesDate;
	}

	public void setSalesDate(Date salesDate) {
		this.salesDate = salesDate;
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

	public List<SalesDetail> getSalesDetail() {
		return salesDetail;
	}

	public void setSalesDetail(List<SalesDetail> salesDetail) {
		this.salesDetail = salesDetail;
	}

	@Override
	public String toString() {
		return "Sales [salesCode=" + salesCode + ", salesDate=" + salesDate + ", client=" + client + ", emp=" + emp
				+ ", salesDetail=" + salesDetail + "]";
	}
	
	
   @PrePersist
   public void preActiveYn() {
      this.salesDate = this.salesDate == null ? new Date(System.currentTimeMillis()) : this.salesDate;
   }

}