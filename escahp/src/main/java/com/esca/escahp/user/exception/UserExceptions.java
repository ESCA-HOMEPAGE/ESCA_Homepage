package com.esca.escahp.user.exception;

import com.esca.escahp.exception.CustomException;
import org.springframework.http.HttpStatus;

public enum UserExceptions implements CustomException {
    NOT_FOUND_USER("유효하지 않은 유저 정보입니다.", HttpStatus.UNAUTHORIZED.value());

    private final String message;
    private final int status;

    UserExceptions(String message, int status) {
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
