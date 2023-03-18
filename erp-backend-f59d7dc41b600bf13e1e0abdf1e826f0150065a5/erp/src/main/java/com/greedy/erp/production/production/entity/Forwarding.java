package com.greedy.erp.production.production.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.greedy.erp.regist.entity.Emp;
import com.greedy.erp.regist.entity.Storage;


@Entity
@Table(name = "FORWARDING")
@SequenceGenerator(
		name = "FORWARDING_SEQ_GENERATOR",
		sequenceName = "SEQ_FORWARDING_CODE",
		initialValue = 1, allocationSize = 1
		)
public class Forwarding {
	
	@Id
	@Column(name = "FORWARDING_CODE")
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "FORWARDING_SEQ_GENERATOR"
		)
	private int forwardingCode;
	
	@Column(name = "FORWARDING_DATE")
	private Date forwardingDate;
	
	
	
	@Column(name = "EMP_CODE", insertable = false, updatable = false)
	private int empCode;
	
	@Column(name = "OUTSTORAGE_CODE")
	private int outStorageCode;
	
	@Column(name = "INSTORAGE_CODE")
	private int inStorageCode;
	
	
	@ManyToOne
	@JoinColumn(name = "EMP_CODE")
	private Emp emp;
	
	@ManyToOne
	@JoinColumn(name = "OUTSTORAGE_CODE", insertable = false, updatable = false)
	private Storage outStorage;
	
	@ManyToOne
	@JoinColumn(name = "INSTORAGE_CODE", insertable = false, updatable = false)
	private Storage inStorage;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "FORWARDING_CODE")
	private List<ForwardingDetail> forwardingDetailList;
	
		
	public Forwarding() {
		super();
	}

	

   public Forwarding(int forwardingCode, Date forwardingDate, int empCode, int outStorageCode, int inStorageCode,
			Emp emp, Storage outStorage, Storage inStorage, List<ForwardingDetail> forwardingDetailList) {
		super();
		this.forwardingCode = forwardingCode;
		this.forwardingDate = forwardingDate;
		this.empCode = empCode;
		this.outStorageCode = outStorageCode;
		this.inStorageCode = inStorageCode;
		this.emp = emp;
		this.outStorage = outStorage;
		this.inStorage = inStorage;
		this.forwardingDetailList = forwardingDetailList;
	}



public int getForwardingCode() {
	return forwardingCode;
}



public void setForwardingCode(int forwardingCode) {
	this.forwardingCode = forwardingCode;
}



public Date getForwardingDate() {
	return forwardingDate;
}



public void setForwardingDate(Date forwardingDate) {
	this.forwardingDate = forwardingDate;
}



public int getEmpCode() {
	return empCode;
}



public void setEmpCode(int empCode) {
	this.empCode = empCode;
}



public int getOutStorageCode() {
	return outStorageCode;
}



public void setOutStorageCode(int outStorageCode) {
	this.outStorageCode = outStorageCode;
}



public int getInStorageCode() {
	return inStorageCode;
}



public void setInStorageCode(int inStorageCode) {
	this.inStorageCode = inStorageCode;
}



public Emp getEmp() {
	return emp;
}



public void setEmp(Emp emp) {
	this.emp = emp;
}



public Storage getOutStorage() {
	return outStorage;
}



public void setOutStorage(Storage outStorage) {
	this.outStorage = outStorage;
}



public Storage getInStorage() {
	return inStorage;
}



public void setInStorage(Storage inStorage) {
	this.inStorage = inStorage;
}



public List<ForwardingDetail> getForwardingDetailList() {
	return forwardingDetailList;
}



public void setForwardingDetailList(List<ForwardingDetail> forwardingDetailList) {
	this.forwardingDetailList = forwardingDetailList;
}



@Override
public String toString() {
	return "Forwarding [forwardingCode=" + forwardingCode + ", forwardingDate=" + forwardingDate + ", empCode="
			+ empCode + ", outStorageCode=" + outStorageCode + ", inStorageCode=" + inStorageCode + ", emp=" + emp
			+ ", outStorage=" + outStorage + ", inStorage=" + inStorage + ", forwardingDetailList="
			+ forwardingDetailList + "]";
}



@PrePersist
   public void preActiveYn() {
      this.forwardingDate = this.forwardingDate == null ? new Date(System.currentTimeMillis()) : this.forwardingDate;
   }
	
	
	
}
