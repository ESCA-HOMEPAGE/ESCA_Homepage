package com.esca.escahp.common.exceptions;

import com.esca.escahp.exception.CustomException;
import org.springframework.http.HttpStatus;

public enum BoardExceptions implements CustomException {

    NOT_FOUND_BOARD("해당 게시물을 찾을 수 없습니다.", HttpStatus.NOT_FOUND.value()),
    NOT_FOUND_REPLY("해당 댓글을 찾을 수 없습니다.", HttpStatus.NOT_FOUND.value()),
    FORBIDDEN_DELETE_REPLY("해당 댓글을 삭제할 권한이 없습니다.", HttpStatus.FORBIDDEN.value());

    private final String message;
    private final int status;

    BoardExceptions(String message, int status) {
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
