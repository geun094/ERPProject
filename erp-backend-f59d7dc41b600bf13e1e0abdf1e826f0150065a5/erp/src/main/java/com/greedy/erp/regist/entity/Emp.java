package com.greedy.erp.regist.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.greedy.erp.authority.entity.Authority;

@Entity
@Table(name = "EMP")
@SequenceGenerator(
		name = "EMP_SEQ_GENERATOR",
		sequenceName = "SEQ_EMP_CODE",
		initialValue = 1, allocationSize = 1
		)
public class Emp {

	@Id
	@Column(name = "EMP_CODE")
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "EMP_SEQ_GENERATOR"
		)
	private int empCode;
	
	@Column(name = "EMP_PW")
	private String empPw;
	
	@Column(name = "EMP_NAME")
	private String empName;
	
	@Column(name = "EMP_RRN")
	private String empRrn;
	
	@Column(name = "EMP_ENTDATE")
	private Date empEntDate;
	
	@Column(name = "EMP_RETDATE")
	private Date empRetDate;
	
	@Column(name = "EMP_PHONE")
	private String empPhone; 
	
	@Column(name = "EMP_MOBILE")
	private String empMobile;
	
	@Column(name = "EMP_EMAIL")
	private String empEmail;
	
	@Column(name = "EMP_ADDRESS")
	private String empAddress;
	
	@Column(name = "EMP_IMAGE")
	private String empImage;
	
	@Column(name = "EMP_ACCOUNT")
	private String empAccount;
	
	@Column(name = "EMP_THEME")
	private String empTheme;
	
	@Column(name = "EMP_STAMP")
	private String empStamp;
	
	@ManyToOne
	@JoinColumn(name = "DEPT_CODE")
	private Dept dept;
	
	@ManyToOne
	@JoinColumn(name = "POSITION_CODE")
	private Position position;
	
	@OneToMany
	@JoinColumn(name = "EMP_CODE")
	private List<Authority> empRole;

	public Emp() {
		super();
	}

	public Emp(int empCode, String empPw, String empName, String empRrn, Date empEntDate, Date empRetDate,
			String empPhone, String empMobile, String empEmail, String empAddress, String empImage, String empAccount,
			String empTheme, String empStamp, Dept dept, Position position, List<Authority> empRole) {
		super();
		this.empCode = empCode;
		this.empPw = empPw;
		this.empName = empName;
		this.empRrn = empRrn;
		this.empEntDate = empEntDate;
		this.empRetDate = empRetDate;
		this.empPhone = empPhone;
		this.empMobile = empMobile;
		this.empEmail = empEmail;
		this.empAddress = empAddress;
		this.empImage = empImage;
		this.empAccount = empAccount;
		this.empTheme = empTheme;
		this.empStamp = empStamp;
		this.dept = dept;
		this.position = position;
		this.empRole = empRole;
	}

	public int getEmpCode() {
		return empCode;
	}

	public void setEmpCode(int empCode) {
		this.empCode = empCode;
	}

	public String getEmpPw() {
		return empPw;
	}

	public void setEmpPw(String empPw) {
		this.empPw = empPw;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpRrn() {
		return empRrn;
	}

	public void setEmpRrn(String empRrn) {
		this.empRrn = empRrn;
	}

	public Date getEmpEntDate() {
		return empEntDate;
	}

	public void setEmpEntDate(Date empEntDate) {
		this.empEntDate = empEntDate;
	}

	public Date getEmpRetDate() {
		return empRetDate;
	}

	public void setEmpRetDate(Date empRetDate) {
		this.empRetDate = empRetDate;
	}

	public String getEmpPhone() {
		return empPhone;
	}

	public void setEmpPhone(String empPhone) {
		this.empPhone = empPhone;
	}

	public String getEmpMobile() {
		return empMobile;
	}

	public void setEmpMobile(String empMobile) {
		this.empMobile = empMobile;
	}

	public String getEmpEmail() {
		return empEmail;
	}

	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}

	public String getEmpAddress() {
		return empAddress;
	}

	public void setEmpAddress(String empAddress) {
		this.empAddress = empAddress;
	}

	public String getEmpImage() {
		return empImage;
	}

	public void setEmpImage(String empImage) {
		this.empImage = empImage;
	}

	public String getEmpAccount() {
		return empAccount;
	}

	public void setEmpAccount(String empAccount) {
		this.empAccount = empAccount;
	}

	public String getEmpTheme() {
		return empTheme;
	}

	public void setEmpTheme(String empTheme) {
		this.empTheme = empTheme;
	}

	public String getEmpStamp() {
		return empStamp;
	}

	public void setEmpStamp(String empStamp) {
		this.empStamp = empStamp;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public List<Authority> getEmpRole() {
		return empRole;
	}

	public void setEmpRole(List<Authority> empRole) {
		this.empRole = empRole;
	}

	@Override
	public String toString() {
		return "Emp [empCode=" + empCode + ", empPw=" + empPw + ", empName=" + empName + ", empRrn=" + empRrn
				+ ", empEntDate=" + empEntDate + ", empRetDate=" + empRetDate + ", empPhone=" + empPhone
				+ ", empMobile=" + empMobile + ", empEmail=" + empEmail + ", empAddress=" + empAddress + ", empImage="
				+ empImage + ", empAccount=" + empAccount + ", empTheme=" + empTheme + ", empStamp=" + empStamp
				+ ", dept=" + dept + ", position=" + position + ", empRole=" + empRole + "]";
	}

}
