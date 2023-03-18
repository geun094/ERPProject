package com.greedy.erp.jwt;

import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.greedy.erp.authority.dto.TokenDTO;
import com.greedy.erp.authority.entity.Authority;
import com.greedy.erp.exception.TokenException;
import com.greedy.erp.regist.entity.Emp;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class TokenProvider {
	
	private static final Logger log = LoggerFactory.getLogger(TokenProvider.class);
	private static final String AUTHORITIES_KEY = "key";
	private static final String BEARER_TYPE = "Bearer";
	private static final long ACCESS_TOKEN_EXPIRE_TIME = 10000 * 60 * 30; // 30분

	private final UserDetailsService userDetailsService;
	
	private final Key key;
	
	public TokenProvider(@Value("${jwt.secret}")String secretKey, 
						 UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		this.key = Keys.hmacShaKeyFor(keyBytes);
	}
	
	// 1. 토큰 생성 메소드
	public TokenDTO generateTokenDTO(Emp emp) {
		
		log.info("[TokenProveder] generateTokenDTO 수따또~~~~~~~~~~~~~~~~~~~~~~");
		
		List<String> roles = new ArrayList<>();
		for(Authority empRole : emp.getEmpRole()) {
			roles.add(empRole.getAuthority().getAuthName());
		}
		
		log.info("[TokenProvider] authorities {}", roles);
		
		// 1. 회원 아이디를 "sub"이라는 클레임으로 토큰에 추가
		Claims claims = Jwts.claims().setSubject(String.valueOf(emp.getEmpCode()));
		
		// 2. 회원의 권한들을 "auth"라는 클레임으로 토큰에 추가
		claims.put(AUTHORITIES_KEY, roles);
		
		long now = System.currentTimeMillis();
		
		Date accessTokenExpiresIn = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
		String accessToken = Jwts.builder()
										   .setClaims(claims)
										   
										   // 3. 토큰의 만료기간을 DATE형으로 토큰에 추가
										   //    ("exp"라는 클레임으로 long형으로 토큰에 추가)
										   .setExpiration(accessTokenExpiresIn)
										   .signWith(key, SignatureAlgorithm.HS512)
										   .compact();
		
		log.info("[TokenProveder] generateTokenDTO 끄ㅜ으으으으읕~~~~~~~~~~~~~~~~~~~~~~");
		
		return new TokenDTO(BEARER_TYPE, String.valueOf(emp.getEmpCode()), accessToken, 
							accessTokenExpiresIn.getTime());
	}
	
	// 2. 토큰의 등록된 클레임의 subject에서 해당 사원의 사번을 추출하는 메서드.
	public String getEmpCode(String token) {
		return Jwts.parserBuilder()
							.setSigningKey(key).build()
							.parseClaimsJwt(token)
							.getBody()		// payload의 claim 추출
							.getSubject();  // Claims중에 등록 클레임에 해당하는 sub값 추출(회원 아이디)
	}
	
	// 3. AccessToken으로 인증 객체 추출(이 클래스의 5번과 2번에 해당하는 메소드를 사용)
	public Authentication getAuthentication(String token) {
		
		log.info("[TokenProvider] getAuthentication 수따또~~~~~~~~~~~~~~~~~~~");
		
		// 토큰에서 claim들 추출(토큰 복호화)
		Claims claims = parseClaims(token);
		
		if(claims.get(AUTHORITIES_KEY) == null) {
			throw new RuntimeException("권한 정보가 없는 토큰입니다.");
		}
		
		// 클레임에서 권한 정보 가져오기
		Collection<? extends GrantedAuthority> authorities = 
				// ex: "ROLE_ADMIN"이랑 "ROLE_MEMBER"같은 문자열이 들어있는 문자열 배열
				Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
				// 문자열 배열에 들어있는 권한 문자열 마다 SimpleGrantedAuthority 객체로 만듦
					  .map(role -> new SimpleGrantedAuthority(role))
				// List<SimpleGrantedAuthority>로 만듦
					  .collect(Collectors.toList());
		
		log.info("[TokenProvider] authorities {}", authorities);
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(this.getEmpCode(token));
		
		log.info("[TokenProvider] getAuthentication 끄으으으으으으으읕~~~~~~~~~~~");
		
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}
	
	// 4. 토큰 유효성 검사
	public boolean validateToken(String token) {
		
		try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return true;
		} catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
			log.info("[TokenProvider] 잘못된 JWT 서명입니다.");
			throw new TokenException("잘못된 JWT 서명입니다.");
		} catch (ExpiredJwtException e) {
			log.info("[TokenProvider] 만료된 JWT 토큰입니다.");
			throw new TokenException("만료된 JWT 토큰입니다.");
		} catch (UnsupportedJwtException e) {
			log.info("[TokenProvider] 지원되지 않는 JWT 토큰입니다.");
			throw new TokenException("지원되지 않는 JWT 토큰입니다.");
		} catch (IllegalArgumentException e) {
			log.info("[TokenProvider] JWT 토큰이 잘못되었습니다.");
			throw new TokenException("JWT 토큰이 잘못되었습니다.");
		}

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// 5. AccessToken에서 클레임 추출하는 메소드
	private Claims parseClaims(String token) {
		try {
			return Jwts.parserBuilder().setSigningKey(key)
									   .build()
									   .parseClaimsJws(token)
									   .getBody();
		} catch (ExpiredJwtException e) {
			// 토큰이 만료되어 예외가 발생하더라도 클레임 값들은 뽑을 수 있다.
			return e.getClaims(); 			
		}
	}

	
	
}
