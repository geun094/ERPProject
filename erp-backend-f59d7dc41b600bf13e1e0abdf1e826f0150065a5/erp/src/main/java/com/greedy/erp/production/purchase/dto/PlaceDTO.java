package com.greedy.erp.production.purchase.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.PrePersist;

import com.greedy.erp.production.purchase.entity.PlaceDetail;
import com.greedy.erp.regist.dto.ClientDTO;
import com.greedy.erp.regist.dto.EmpDTO;
import com.greedy.erp.regist.dto.StorageDTO;

public class PlaceDTO {
	private int placeCode;
	private Date placeDate;
	private Date placeDelivery;
	private String placeStatus;
	private ClientDTO client;
	private EmpDTO emp;
	private StorageDTO storage;
	private List<PlaceDetail> placeDetail;
	
	public PlaceDTO () {
		super();
	}
	
	public PlaceDTO(int placeCode, Date placeDate, Date placeDelivery, String placeStatus, ClientDTO client, EmpDTO emp,
			StorageDTO storage, List<PlaceDetail> placeDetail) {
		super();
		this.placeCode = placeCode;
		this.placeDate = placeDate;
		this.placeDelivery = placeDelivery;
		this.placeStatus = placeStatus;
		this.client = client;
		this.emp = emp;
		this.storage = storage;
		this.placeDetail = placeDetail;
	}
	public int getPlaceCode() {
		return placeCode;
	}
	public void setPlaceCode(int placeCode) {
		this.placeCode = placeCode;
	}
	public Date getPlaceDate() {
		return placeDate;
	}
	public void setPlaceDate(Date placeDate) {
		this.placeDate = placeDate;
	}
	public Date getPlaceDelivery() {
		return placeDelivery;
	}
	public void setPlaceDelivery(Date placeDelivery) {
		this.placeDelivery = placeDelivery;
	}
	public String getPlaceStatus() {
		return placeStatus;
	}
	public void setPlaceStatus(String placeStatus) {
		this.placeStatus = placeStatus;
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
	public List<PlaceDetail> getPlaceDetail() {
		return placeDetail;
	}
	public void setPlaceDetail(List<PlaceDetail> placeDetail) {
		this.placeDetail = placeDetail;
	}
	@Override
	public String toString() {
		return "PlaceDTO [placeCode=" + placeCode + ", placeDate=" + placeDate + ", placeDelivery=" + placeDelivery
				+ ", placeStatus=" + placeStatus + ", client=" + client + ", emp=" + emp + ", storage=" + storage
				+ ", placeDetail=" + placeDetail + "]";
	}
	
	
	
	
}
