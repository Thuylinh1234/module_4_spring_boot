package com.sqc.academy.baikiemtra.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@FieldDefaults(makeFinal = true)
public enum ErrorCode {
    MATBANG_NOT_FOUND(40401, "Premises not found", HttpStatus.NOT_FOUND);

    public int code;
    public String message;
    public HttpStatus status;
}
