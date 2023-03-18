package com.greedy.erp.production.business.dto;

import java.util.Date;
import java.util.List;

import com.greedy.erp.production.business.entity.EstimateDetail;
import com.greedy.erp.regist.dto.ClientDTO;
import com.greedy.erp.regist.dto.EmpDTO;
import com.greedy.erp.regist.dto.StorageDTO;

public class EstimateDTO {
	
	private int estimateCode;
	private Date estimateDate;
	private String estimateStatus;
	
	private ClientDTO client;
	private EmpDTO emp;
	private StorageDTO storage;
	
	private List<EstimateDetail> estimateDetail;
	
	public EstimateDTO() {
		super();
	}

	public EstimateDTO(int estimateCode, Date estimateDate, String estimateStatus, ClientDTO client, EmpDTO emp,
			StorageDTO storage, List<EstimateDetail> estimateDetail) {
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

	public StorageDTO getStorage() {
		return storage;
	}

	public void setStorage(StorageDTO storage) {
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
		return "EstimateDTO [estimateCode=" + estimateCode + ", estimateDate=" + estimateDate + ", estimateStatus="
				+ estimateStatus + ", client=" + client + ", emp=" + emp + ", storage=" + storage + ", estimateDetail="
				+ estimateDetail + "]";
	}
	
	
	
	
}