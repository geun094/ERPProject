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
@Table(name = "ORDERS")
@SequenceGenerator(
		name = "ORDERS_SEQ_GENERATOR",
		sequenceName = "SEQ_ORDERS_CODE",
		initialValue = 1, allocationSize = 1
		)
public class Orders {
	
	@Id
	@Column( name = "ORDERS_CODE")
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "ORDERS_SEQ_GENERATOR"
		)
	private int ordersCode;
	
	@Column( name = "ORDERS_DATE")
	private Date ordersDate;
	
	@Column( name = "ORDERS_DELIVERY")
	private Date ordersDelivery;
	
	@Column( name = "ORDERS_STATUS")
	private String ordersStatus;
	
	@ManyToOne
	@JoinColumn( name = "CLIENT_CODE")
	private Client client;
	
	@ManyToOne
	@JoinColumn( name = "EMP_CODE")
	private Emp emp;
	
	@ManyToOne
	@JoinColumn( name = "STORAGE_CODE")
	private Storage storage;
	
	@OneToMany (cascade = CascadeType.ALL)
	@JoinColumn( name = "ORDERS_CODE")
	private List<OrdersDetail> ordersDetail;
		
	public Orders() {}

	public Orders(int ordersCode, Date ordersDate, Date ordersDelivery, String ordersStatus, Client client, Emp emp,
			Storage storage, List<OrdersDetail> ordersDetail) {
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

	public List<OrdersDetail> getOrdersDetail() {
		return ordersDetail;
	}

	public void setOrdersDetail(List<OrdersDetail> ordersDetail) {
		this.ordersDetail = ordersDetail;
	}

	@Override
	public String toString() {
		return "Orders [ordersCode=" + ordersCode + ", ordersDate=" + ordersDate + ", ordersDelivery=" + ordersDelivery
				+ ", ordersStatus=" + ordersStatus + ", client=" + client + ", emp=" + emp + ", storage=" + storage
				+ ", ordersDetail=" + ordersDetail + "]";
	}
	
   @PrePersist
   public void preActiveYn() {
      this.ordersDate = this.ordersDate == null ? new Date(System.currentTimeMillis()) : this.ordersDate;
   }

}