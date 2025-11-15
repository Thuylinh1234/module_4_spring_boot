package com.sqc.academy.bai4;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    EMPLOYEE_NOT_FOUND(40401, "Employee not found", HttpStatus.NOT_FOUND);

    private final int code;
    private final String message;
    private final HttpStatus status;

    // Constructor
    ErrorCode(int code, String message, HttpStatus status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

    // Getter
    public int getCode() { return code; }
    public String getMessage() { return message; }
    public HttpStatus getStatus() { return status; }
}
