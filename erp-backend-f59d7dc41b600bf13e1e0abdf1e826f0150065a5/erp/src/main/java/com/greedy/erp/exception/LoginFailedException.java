package com.greedy.erp.exception;


/* RuntimeException을 상속 받아 예외 발생 시 throws로 처리하지 않아도 되도록 하자. */
public class LoginFailedException extends RuntimeException{

	
	
	public LoginFailedException(String message) {
		super(message);
	}
}
