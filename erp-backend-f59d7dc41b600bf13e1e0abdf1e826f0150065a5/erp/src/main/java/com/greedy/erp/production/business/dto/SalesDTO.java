package com.greedy.erp.production.business.dto;

import java.util.Date;
import java.util.List;

import com.greedy.erp.production.business.entity.SalesDetail;
import com.greedy.erp.regist.dto.ClientDTO;
import com.greedy.erp.regist.dto.EmpDTO;
import com.greedy.erp.regist.dto.StorageDTO;

public class SalesDTO {
	
	private int salesCode;
	private Date salesDate;
	
	private ClientDTO client;
	private EmpDTO emp;
	
	private List<SalesDetail> salesDetail;
	
	public SalesDTO() {}

	public SalesDTO(int salesCode, Date salesDate, ClientDTO client, EmpDTO emp, List<SalesDetail> salesDetail) {
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

	public List<SalesDetail> getSalesDetail() {
		return salesDetail;
	}

	public void setSalesDetail(List<SalesDetail> salesDetail) {
		this.salesDetail = salesDetail;
	}

	@Override
	public String toString() {
		return "SalesDTO [salesCode=" + salesCode + ", salesDate=" + salesDate + ", client=" + client + ", emp=" + emp
				+ ", salesDetail=" + salesDetail + "]";
	}

}