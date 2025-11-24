package com.sqc.academy.baikiemtra.exception;

import com.sqc.academy.ApiRespone;
import com.sqc.academy.bai4.dto.ApiResponse;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Getter
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> handleException(ApiException exception){
        ErrorCode errorCode = exception.getErrorCode();
        return ResponseEntity.status(errorCode.getStatus()).body(
                ApiResponse.builder()
                        .code(errorCode.getCode())
                        .message(errorCode.getMessage())
                        .build());
    }
}
