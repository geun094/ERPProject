package com.greedy.erp.authority.dto;

public class TokenDTO {

	private String grantType;			// 토큰 타입
	private String empCode; 			// 인증받은 회원 이름
	private String accessToken; 		// 액세스 토큰
	private Long accessTokenExpiresIn;	// Long 형의 만료 시간
	
	public TokenDTO() {
		super();
	}

	public TokenDTO(String grantType, String empCode, String accessToken, Long accessTokenExpiresIn) {
		super();
		this.grantType = grantType;
		this.empCode = empCode;
		this.accessToken = accessToken;
		this.accessTokenExpiresIn = accessTokenExpiresIn;
	}

	public String getGrantType() {
		return grantType;
	}

	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Long getAccessTokenExpiresIn() {
		return accessTokenExpiresIn;
	}

	public void setAccessTokenExpiresIn(Long accessTokenExpiresIn) {
		this.accessTokenExpiresIn = accessTokenExpiresIn;
	}

	@Override
	public String toString() {
		return "TokenDTO [grantType=" + grantType + ", empCode=" + empCode + ", accessToken=" + accessToken
				+ ", accessTokenExpiresIn=" + accessTokenExpiresIn + "]";
	}

	
	
}
