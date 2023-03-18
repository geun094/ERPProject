package com.greedy.erp.regist.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "CLIENT")
@SequenceGenerator(
		name = "CLIENT_SEQ_GENERATOR",
		sequenceName = "SEQ_CLIENT_CODE",
		initialValue = 1, allocationSize = 1
		)
public class Client {
	
	@Id
	@Column(name = "CLIENT_CODE")
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "CLIENT_SEQ_GENERATOR"
		)
	private int clientCode;
	
	@Column(name = "CLIENT_NAME")
	private String clientName;
	
	@Column(name = "CLIENT_REPRESENTATIVE")
	private String clientRepresentative;
	
	@Column(name = "CLIENT_TYPE")
	private String clientType;
	
	@Column(name = "CLIENT_ITEM")
	private String clientItem;
	
	@Column(name = "CLIENT_PHONE")
	private String clientPhone;
	
	@Column(name = "CLIENT_MOBILE")
	private String clientMobile;
	
	@Column(name = "CLIENT_FAX")
	private String clientFax;
	
	@Column(name = "CLIENT_ADDRESS")
	private String clientAddress;
	
	@Column(name = "CLIENT_ACCOUNT")
	private String clientAccount;
	
	@Column(name = "CLIENT_WEB")
	private String clientWeb;
	
	@Column(name = "CLIENT_EMAIL")
	private String clientEmail;
	
	@ManyToOne
	@JoinColumn(name = "EMP_CODE")
	private Emp emp;
	
	public Client() {}

	public Client(int clientCode, String clientName, String clientRepresentative, String clientType, String clientItem,
			String clientPhone, String clientMobile, String clientFax, String clientAddress, String clientAccount,
			String clientWeb, String clientEmail, Emp emp) {
		super();
		this.clientCode = clientCode;
		this.clientName = clientName;
		this.clientRepresentative = clientRepresentative;
		this.clientType = clientType;
		this.clientItem = clientItem;
		this.clientPhone = clientPhone;
		this.clientMobile = clientMobile;
		this.clientFax = clientFax;
		this.clientAddress = clientAddress;
		this.clientAccount = clientAccount;
		this.clientWeb = clientWeb;
		this.clientEmail = clientEmail;
		this.emp = emp;
	}

	public int getClientCode() {
		return clientCode;
	}

	public void setClientCode(int clientCode) {
		this.clientCode = clientCode;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientRepresentative() {
		return clientRepresentative;
	}

	public void setClientRepresentative(String clientRepresentative) {
		this.clientRepresentative = clientRepresentative;
	}

	public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

	public String getClientItem() {
		return clientItem;
	}

	public void setClientItem(String clientItem) {
		this.clientItem = clientItem;
	}

	public String getClientPhone() {
		return clientPhone;
	}

	public void setClientPhone(String clientPhone) {
		this.clientPhone = clientPhone;
	}

	public String getClientMobile() {
		return clientMobile;
	}

	public void setClientMobile(String clientMobile) {
		this.clientMobile = clientMobile;
	}

	public String getClientFax() {
		return clientFax;
	}

	public void setClientFax(String clientFax) {
		this.clientFax = clientFax;
	}

	public String getClientAddress() {
		return clientAddress;
	}

	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}

	public String getClientAccount() {
		return clientAccount;
	}

	public void setClientAccount(String clientAccount) {
		this.clientAccount = clientAccount;
	}

	public String getClientWeb() {
		return clientWeb;
	}

	public void setClientWeb(String clientWeb) {
		this.clientWeb = clientWeb;
	}

	public String getClientEmail() {
		return clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}

	public Emp getEmp() {
		return emp;
	}

	public void setEmp(Emp emp) {
		this.emp = emp;
	}

	@Override
	public String toString() {
		return "Client [clientCode=" + clientCode + ", clientName=" + clientName + ", clientRepresentative="
				+ clientRepresentative + ", clientType=" + clientType + ", clientItem=" + clientItem + ", clientPhone="
				+ clientPhone + ", clientMobile=" + clientMobile + ", clientFax=" + clientFax + ", clientAddress="
				+ clientAddress + ", clientAccount=" + clientAccount + ", clientWeb=" + clientWeb + ", clientEmail="
				+ clientEmail + ", emp=" + emp + "]";
	}

}