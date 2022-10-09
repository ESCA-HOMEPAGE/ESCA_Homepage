package com.esca.escahp.schedule.exceptions;

import com.esca.escahp.exception.CustomException;
import org.springframework.http.HttpStatus;

public enum ScheduleExceptions implements CustomException {
    NOT_FOUND_SCHEDULE("해당 연간일정을 찾을 수 없습니다", HttpStatus.NOT_FOUND.value());

    private final String message;
    private final int status;

    ScheduleExceptions(String message, int status) {
        this.message = message;
        this.status = status;
    }

    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public int getStatus() {
        return 0;
    }
}
