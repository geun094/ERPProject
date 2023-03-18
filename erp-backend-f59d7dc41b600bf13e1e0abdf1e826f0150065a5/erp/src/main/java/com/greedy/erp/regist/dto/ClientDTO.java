package com.greedy.erp.regist.dto;

import java.util.Date;

public class ClientDTO {
	private int clientCode;
	private String clientName;
	private String clientRepresentative;
	private String clientType;
	private String clientItem;
	private String clientPhone;
	private String clientMobile;
	private String clientFax;
	private String clientAddress;
	private String clientAccount;
	private String clientWeb;
	private String clientEmail;
	private EmpDTO emp;
	
	public ClientDTO() {

	}

	public ClientDTO(int clientCode, String clientName, String clientRepresentative, String clientType,
			String clientItem, String clientPhone, String clientMobile, String clientFax, String clientAddress,
			String clientAccount, String clientWeb, String clientEmail, EmpDTO emp) {
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

	public EmpDTO getEmp() {
		return emp;
	}

	public void setEmp(EmpDTO emp) {
		this.emp = emp;
	}

	@Override
	public String toString() {
		return "ClientDTO [clientCode=" + clientCode + ", clientName=" + clientName + ", clientRepresentative="
				+ clientRepresentative + ", clientType=" + clientType + ", clientItem=" + clientItem + ", clientPhone="
				+ clientPhone + ", clientMobile=" + clientMobile + ", clientFax=" + clientFax + ", clientAddress="
				+ clientAddress + ", clientAccount=" + clientAccount + ", clientWeb=" + clientWeb + ", clientEmail="
				+ clientEmail + ", emp=" + emp + "]";
	}

}