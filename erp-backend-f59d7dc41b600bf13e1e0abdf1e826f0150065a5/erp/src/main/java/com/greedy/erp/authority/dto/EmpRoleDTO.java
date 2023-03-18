package com.greedy.erp.authority.dto;

public class EmpRoleDTO {
	
	private int memberNo;
	// 엔티티를 작성하고 복합키 설정에 용이하기 위함이자 MemberRole insert나 update에서 필수!
	private int authorityCode;		
	
	private AuthorityDTO authority;

	public EmpRoleDTO() {
		super();
	}

	public EmpRoleDTO(int memberNo, int authorityCode, AuthorityDTO authority) {
		super();
		this.memberNo = memberNo;
		this.authorityCode = authorityCode;
		this.authority = authority;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public int getAuthorityCode() {
		return authorityCode;
	}

	public void setAuthorityCode(int authorityCode) {
		this.authorityCode = authorityCode;
	}

	public AuthorityDTO getAuthority() {
		return authority;
	}

	public void setAuthority(AuthorityDTO authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "EmpRoleDTO [memberNo=" + memberNo + ", authorityCode=" + authorityCode + ", authority=" + authority
				+ "]";
	}
	
	


}
