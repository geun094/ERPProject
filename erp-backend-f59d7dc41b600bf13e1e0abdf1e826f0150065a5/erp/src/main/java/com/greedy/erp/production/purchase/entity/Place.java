package com.greedy.erp.production.purchase.entity;

import java.util.Date;
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

import com.greedy.erp.production.business.entity.OrdersDetail;
import com.greedy.erp.regist.entity.Client;
import com.greedy.erp.regist.entity.Emp;
import com.greedy.erp.regist.entity.Storage;

@Entity
@Table(name = "PLACE")
@SequenceGenerator(
		name = "PLACE_SEQ_GENERATOR",
		sequenceName = "SEQ_PLACE_CODE",
		initialValue = 1, allocationSize = 1
		)

public class Place {
	
	@Id
	@Column( name = "PLACE_CODE")
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "PLACE_SEQ_GENERATOR"
		)
	private int placeCode;
	
	@Column(name = "PLACE_DATE")
	private Date placeDate;
	
	@Column(name = "PLACE_DELIVERY")
	private Date placeDelivery;

	@Column(name = "PLACE_STATUS")
	private String placeStatus;
	
	@ManyToOne
	@JoinColumn(name = "CLIENT_NO")
	private Client client;
	
	@ManyToOne
	@JoinColumn(name = "EMP_CODE")
	private Emp emp;
			
	@ManyToOne
	@JoinColumn(name = "STORAGE_CODE")
	private Storage storage;

	@OneToMany (cascade = CascadeType.ALL)
	@JoinColumn( name = "PLACE_CODE")
	private List<PlaceDetail> placeDetail;
	
	public Place() {}
	
	
	
	public Place(int placeCode, Date placeDate, Date placeDelivery, String placeStatus, Client client, Emp emp,
			Storage storage, List<PlaceDetail> placeDetail) {
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



	public List<PlaceDetail> getPlaceDetail() {
		return placeDetail;
	}



	public void setPlaceDetail(List<PlaceDetail> placeDetail) {
		this.placeDetail = placeDetail;
	}



	@Override
	public String toString() {
		return "Place [placeCode=" + placeCode + ", placeDate=" + placeDate + ", placeDelivery=" + placeDelivery
				+ ", placeStatus=" + placeStatus + ", client=" + client + ", emp=" + emp + ", storage=" + storage
				+ ", placeDetail=" + placeDetail + "]";
	}



	@PrePersist
	   public void preActiveYn() {
	      this.placeDate = this.placeDate == null ? new Date(System.currentTimeMillis()) : this.placeDate;
	   }
}
