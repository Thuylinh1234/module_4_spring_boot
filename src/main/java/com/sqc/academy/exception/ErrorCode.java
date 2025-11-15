package com.sqc.academy.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@FieldDefaults(makeFinal = true)
public enum ErrorCode {
    STUDENT_NOT_FOUND(40401, "Student is not exist", HttpStatus.NOT_FOUND),
    TEACHER_NOT_FOUND(40402, "Student is not exist", HttpStatus.NOT_FOUND)
    ; // ngăn cách nhau các structor bằng ;
    int code;
    String message;
    HttpStatus status;

}
