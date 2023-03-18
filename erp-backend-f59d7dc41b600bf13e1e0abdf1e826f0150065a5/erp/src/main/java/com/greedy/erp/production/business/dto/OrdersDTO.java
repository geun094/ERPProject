package com.greedy.erp.production.business.dto;

import java.util.Date;
import java.util.List;

import com.greedy.erp.production.business.entity.OrdersDetail;
import com.greedy.erp.regist.dto.ClientDTO;
import com.greedy.erp.regist.dto.EmpDTO;
import com.greedy.erp.regist.dto.StorageDTO;

public class OrdersDTO {
	
	private int ordersCode;
	private Date ordersDate;
	private Date ordersDelivery;
	private String ordersStatus;
	
	private ClientDTO client;
	private EmpDTO emp;
	private StorageDTO storage;
	
	private List<OrdersDetail> ordersDetail;
	
	public OrdersDTO() {
		super();
	}

	public OrdersDTO(int ordersCode, Date ordersDate, Date ordersDelivery, String ordersStatus, ClientDTO client,
			EmpDTO emp, StorageDTO storage, List<OrdersDetail> ordersDetail) {
		super();
		this.ordersCode = ordersCode;
		this.ordersDate = ordersDate;
		this.ordersDelivery = ordersDelivery;
		this.ordersStatus = ordersStatus;
		this.client = client;
		this.emp = emp;
		this.storage = storage;
		this.ordersDetail = ordersDetail;
	}

	public int getOrdersCode() {
		return ordersCode;
	}

	public void setOrdersCode(int ordersCode) {
		this.ordersCode = ordersCode;
	}

	public Date getOrdersDate() {
		return ordersDate;
	}

	public void setOrdersDate(Date ordersDate) {
		this.ordersDate = ordersDate;
	}

	public Date getOrdersDelivery() {
		return ordersDelivery;
	}

	public void setOrdersDelivery(Date ordersDelivery) {
		this.ordersDelivery = ordersDelivery;
	}

	public String getOrdersStatus() {
		return ordersStatus;
	}

	public void setOrdersStatus(String ordersStatus) {
		this.ordersStatus = ordersStatus;
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

	public List<OrdersDetail> getOrdersDetail() {
		return ordersDetail;
	}

	public void setOrdersDetail(List<OrdersDetail> ordersDetail) {
		this.ordersDetail = ordersDetail;
	}

	@Override
	public String toString() {
		return "OrdersDTO [ordersCode=" + ordersCode + ", ordersDate=" + ordersDate + ", ordersDelivery="
				+ ordersDelivery + ", ordersStatus=" + ordersStatus + ", client=" + client + ", emp=" + emp
				+ ", storage=" + storage + ", ordersDetail=" + ordersDetail + "]";
	}

}