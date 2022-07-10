package com.esca.escahp.exception;

public class EscaException extends RuntimeException {

    private final CustomException customException;

    public EscaException(CustomException customException) {
        this.customException = customException;
    }

    @Override
    public String getMessage() {
        return customException.getMessage();
    }

    public int getStatus() {
        return customException.getStatus();
    }
}
