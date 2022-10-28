package com.esca.escahp.user.exception;

import com.esca.escahp.exception.CustomException;
import org.springframework.http.HttpStatus;

public enum MailExceptions implements CustomException {
    CANNOT_SEND_MAIL("이메일 전송에 실패했습니다", HttpStatus.BAD_REQUEST.value())
    ;

    private final String message;
    private final int status;

    MailExceptions(String message, int status) {
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
