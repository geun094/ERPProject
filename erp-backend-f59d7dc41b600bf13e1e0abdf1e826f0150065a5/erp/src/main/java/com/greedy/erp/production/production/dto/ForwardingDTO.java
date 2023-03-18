package com.greedy.erp.production.production.dto;

import java.sql.Date;
import java.util.List;

import com.greedy.erp.production.production.entity.ForwardingDetail;
import com.greedy.erp.production.production.entity.WorkDetail;
import com.greedy.erp.regist.entity.Client;
import com.greedy.erp.regist.entity.Emp;
import com.greedy.erp.regist.entity.Product;
import com.greedy.erp.regist.entity.Storage;

public class ForwardingDTO {
	
	private int forwardingCode;
	private Date forwardingDate;

	private int empCode;
	private int outStorageCode;
	private int inStorageCode;
	
	private Emp emp;
	private Storage outStorage;
	private Storage inStorage;
	
	private List<ForwardingDetail> forwardingDetailList;

	public ForwardingDTO() {
		super();
	}

	public ForwardingDTO(int forwardingCode, Date forwardingDate, int empCode, int outStorageCode, int inStorageCode,
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
		return "ForwardingDTO [forwardingCode=" + forwardingCode + ", forwardingDate=" + forwardingDate + ", empCode="
				+ empCode + ", outStorageCode=" + outStorageCode + ", inStorageCode=" + inStorageCode + ", emp=" + emp
				+ ", outStorage=" + outStorage + ", inStorage=" + inStorage + ", forwardingDetailList="
				+ forwardingDetailList + "]";
	}

	
	
	

}
