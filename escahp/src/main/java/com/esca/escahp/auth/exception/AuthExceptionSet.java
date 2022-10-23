package com.esca.escahp.auth.exception;

import com.esca.escahp.exception.CustomException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum AuthExceptionSet implements CustomException {
    INVALID_TOKEN("유효하지 않은 토큰입니다", HttpStatus.UNAUTHORIZED.value()),
    ;

    private final String message;
    private final int status;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public int getStatus() {
        return status;
    }
}
