package com.esca.escahp.user.exception;


import com.esca.escahp.exception.CustomException;
import org.springframework.http.HttpStatus;

public enum SignUpExceptions implements CustomException {
	ALREADY_SIGNED_UP("이미 회원가입된 회원입니다.", HttpStatus.BAD_REQUEST.value()),
	NOT_FOUND_PASSWORD("비밀번호가 다릅니다.", HttpStatus.NOT_FOUND.value()),
	NOT_FOUND_ID("아이디가 다릅니다.", HttpStatus.NOT_FOUND.value()),
	ALREADY_EXIST_NICKNAME("중복된 닉네임입니다.", HttpStatus.FORBIDDEN.value()),
	NOT_EXIST_USER("없는 회원입니다.", HttpStatus.NOT_FOUND.value()),
	NOT_FOUND_DATA("아이디나 비밀번호가 다릅니다.", HttpStatus.BAD_REQUEST.value())
	;

	private final String message;
	private final int status;

	SignUpExceptions(String message, int status){
		this.message = message;
		this.status = status;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public int getStatus() {
		return status;
	}
}
