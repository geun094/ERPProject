package com.greedy.erp.regist.dto;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.greedy.erp.authority.dto.EmpRoleDTO;

public class EmpDTO implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	private int empCode;
	private String empPw;
	private String empName;
	private String empRrn;
	private Date empEntdate;
	private Date empRetdate;
	private String empPhone;
	private String empMobile;
	private String empEmail;
	private String empAddress;
	private String empImage;
	private String empAccount;
	private String empTheme;
	private String empStamp;
	private DeptDTO dept;
	private PositionDTO position;
	private List<EmpRoleDTO> empRole;
	
	public EmpDTO() {
		super();
	}
	
	public EmpDTO(int empCode, String empPw, String empName, String empRrn, Date empEntdate, Date empRetdate,
			String empPhone, String empMobile, String empEmail, String empAddress, String empImage, String empAccount,
			String empTheme, String empStamp, DeptDTO dept, PositionDTO position, List<EmpRoleDTO> empRole,
			Collection<GrantedAuthority> authorities) {
		super();
		this.empCode = empCode;
		this.empPw = empPw;
		this.empName = empName;
		this.empRrn = empRrn;
		this.empEntdate = empEntdate;
		this.empRetdate = empRetdate;
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
		this.authorities = authorities;
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

	public Date getEmpEntdate() {
		return empEntdate;
	}

	public void setEmpEntdate(Date empEntdate) {
		this.empEntdate = empEntdate;
	}

	public Date getEmpRetdate() {
		return empRetdate;
	}

	public void setEmpRetdate(Date empRetdate) {
		this.empRetdate = empRetdate;
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

	public DeptDTO getDept() {
		return dept;
	}

	public void setDept(DeptDTO dept) {
		this.dept = dept;
	}

	public PositionDTO getPosition() {
		return position;
	}

	public void setPosition(PositionDTO position) {
		this.position = position;
	}

	public List<EmpRoleDTO> getEmpRole() {
		return empRole;
	}

	public void setEmpRole(List<EmpRoleDTO> empRole) {
		this.empRole = empRole;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "EmpDTO [empCode=" + empCode + ", empPw=" + empPw + ", empName=" + empName + ", empRrn=" + empRrn
				+ ", empEntdate=" + empEntdate + ", empRetdate=" + empRetdate + ", empPhone=" + empPhone
				+ ", empMobile=" + empMobile + ", empEmail=" + empEmail + ", empAddress=" + empAddress + ", empImage="
				+ empImage + ", empAccount=" + empAccount + ", empTheme=" + empTheme + ", empStamp=" + empStamp
				+ ", dept=" + dept + ", position=" + position + ", empRole=" + empRole + ", authorities=" + authorities
				+ "]";
	}

	/* 이하 코드들을 UserDetails로부터 물려받는 추상메소드들을 오버라이딩 한 것이다.(필요한 것만 작성하자) */
	/* MemberDTO는 Member와 매핑 될 DTO이자 UserDetails로써 속성을 추가로 가짐 */
	private Collection<GrantedAuthority> authorities; 
	
	/* setter 추가할 것 */
	public void setAuthorities(Collection<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.empPw;
	}

	@Override
	public String getUsername() {
		return String.valueOf(this.empCode);
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

}
