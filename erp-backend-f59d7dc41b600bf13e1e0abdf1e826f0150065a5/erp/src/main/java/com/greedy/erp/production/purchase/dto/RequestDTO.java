package com.greedy.erp.production.purchase.dto;


import java.util.Date;
import java.util.List;

import com.greedy.erp.production.business.entity.EstimateDetail;
import com.greedy.erp.production.purchase.entity.RequestDetail;
import com.greedy.erp.regist.dto.ClientDTO;
import com.greedy.erp.regist.dto.EmpDTO;
import com.greedy.erp.regist.dto.StorageDTO;

public class RequestDTO {

	private int requestCode;
	private Date requestDate;
	private String requestStatus;
	private ClientDTO client;
	private EmpDTO emp;
	private StorageDTO storage;
	
	private List<RequestDetail> requestDetail;
	
	
	public RequestDTO() {
		super();
	}

	public RequestDTO(int requestCode, Date requestDate, String requestStatus, ClientDTO client, EmpDTO emp,
			StorageDTO storage) {
		super();
		this.requestCode = requestCode;
		this.requestDate = requestDate;
		this.requestStatus = requestStatus;
		this.client = client;
		this.emp = emp;
		this.storage = storage;
		this.requestDetail = requestDetail;
	}

	public int getRequestCode() {
		return requestCode;
	}

	public void setRequestCode(int requestCode) {
		this.requestCode = requestCode;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public String getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
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
	public List<RequestDetail> getRequestDetail() {
		return requestDetail;
	}


	public void setRequestDetail(List<RequestDetail> requestDetail) {
		this.requestDetail = requestDetail;
	}

	@Override
	public String toString() {
		return "RequestDTO [requestCode=" + requestCode + ", requestDate=" + requestDate + ", requestStatus="
				+ requestStatus + ", client=" + client + ", emp=" + emp + ", storage=" + storage + ", requestDetail="
				+ requestDetail + "]";
	}

	
	
	
}
