package com.esca.escahp.user.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SignUpException extends RuntimeException {

	public SignUpException(String message){
		super(message);
	}
}
