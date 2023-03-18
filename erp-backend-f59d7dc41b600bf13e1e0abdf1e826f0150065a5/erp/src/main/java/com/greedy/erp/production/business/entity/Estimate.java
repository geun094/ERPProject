package com.greedy.erp.production.business.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.greedy.erp.regist.entity.Client;
import com.greedy.erp.regist.entity.Emp;
import com.greedy.erp.regist.entity.Storage;


@Entity
@Table(name = "ESTIMATE")
@SequenceGenerator(
		name = "ESTIMATE_SEQ_GENERATOR",
		sequenceName = "SEQ_ESTIMATE_CODE",
		initialValue = 1, allocationSize = 1
		)
public class Estimate {
	
	@Id
	@Column( name = "ESTIMATE_CODE")
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "ESTIMATE_SEQ_GENERATOR"
		)
	private int estimateCode;
	
	@Column( name = "ESTIMATE_DATE")
	private Date estimateDate;
	
	@Column( name = "ESTIMATE_STATUS")
	private String estimateStatus;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn( name = "CLIENT_CODE")
	private Client client;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn( name = "EMP_CODE")
	private Emp emp;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn( name = "STORAGE_CODE")
	private Storage storage;
	
	@OneToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn( name = "ESTIMATE_CODE")
	private List<EstimateDetail> estimateDetail;
		
	public Estimate() {}

	public Estimate(int estimateCode, Date estimateDate, String estimateStatus, Client client, Emp emp, Storage storage,
			List<EstimateDetail> estimateDetail) {
		super();
		this.estimateCode = estimateCode;
		this.estimateDate = estimateDate;
		this.estimateStatus = estimateStatus;
		this.client = client;
		this.emp = emp;
		this.storage = storage;
		this.estimateDetail = estimateDetail;
	}

	public int getEstimateCode() {
		return estimateCode;
	}



	public void setEstimateCode(int estimateCode) {
		this.estimateCode = estimateCode;
	}



	public Date getEstimateDate() {
		return estimateDate;
	}



	public void setEstimateDate(Date estimateDate) {
		this.estimateDate = estimateDate;
	}



	public String getEstimateStatus() {
		return estimateStatus;
	}



	public void setEstimateStatus(String estimateStatus) {
		this.estimateStatus = estimateStatus;
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



	public Storage getStorage() {
		return storage;
	}



	public void setStorage(Storage storage) {
		this.storage = storage;
	}



	public List<EstimateDetail> getEstimateDetail() {
		return estimateDetail;
	}



	public void setEstimateDetail(List<EstimateDetail> estimateDetail) {
		this.estimateDetail = estimateDetail;
	}



	@Override
	public String toString() {
		return "Estimate [estimateCode=" + estimateCode + ", estimateDate=" + estimateDate + ", estimateStatus="
				+ estimateStatus + ", client=" + client + ", emp=" + emp + ", estimateDetail=" + estimateDetail + "]";
	}
	
   @PrePersist
   public void preActiveYn() {
      this.estimateDate = this.estimateDate == null ? new Date(System.currentTimeMillis()) : this.estimateDate;
   }

	
}