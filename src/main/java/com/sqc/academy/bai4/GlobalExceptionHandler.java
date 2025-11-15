package com.sqc.academy.bai4;

import com.sqc.academy.ApiRespone;
import com.sqc.academy.exception.ApiException;
import com.sqc.academy.exception.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {
    @ExceptionHandler(com.sqc.academy.exception.ApiException.class)
    public ResponseEntity<?> handleException(ApiException exception){
        ErrorCode errorCode = exception.getErrorCode();
        return ResponseEntity.status(errorCode.getStatus()).body(
                ApiRespone.builder()
                        .code(errorCode.getCode())
                        .message(errorCode.getMessage())
                        .build());
    }
}
