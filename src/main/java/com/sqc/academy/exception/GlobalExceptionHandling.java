package com.sqc.academy.exception;

import com.sqc.academy.ApiRespone;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandling {
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> handleException(ApiException exception){
        ErrorCode errorCode = exception.getErrorCode();
        return ResponseEntity.status(errorCode.getStatus()).body(
                ApiRespone.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build());
    }
}
