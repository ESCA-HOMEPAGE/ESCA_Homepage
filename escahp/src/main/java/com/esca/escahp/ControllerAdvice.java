package com.esca.escahp;

import com.esca.escahp.exception.ErrorResponse;
import com.esca.escahp.exception.EscaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(EscaException.class)
    public ResponseEntity<ErrorResponse> handleEscaException(EscaException e) {
        logger.warn("CustomException!!: ", e);
        return ResponseEntity.status(e.getStatus())
                .body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRunTimeException(RuntimeException e) {
        logger.error("RuntimeException!!: ", e);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("예상치 못한 에러가 발생했습니다."));
    }
}