package com.sqc.academy.bai4;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@FieldDefaults(makeFinal = true)
public enum ErrorCode {
    EMPLOYEE_NOT_FOUND(40401, "Student is not exist", HttpStatus.NOT_FOUND),
    ; // ngăn cách nhau các structor bằng ;
    int code;
    String message;
    HttpStatus status;

}
