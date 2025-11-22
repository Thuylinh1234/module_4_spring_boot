package com.sqc.academy.bai4;

import com.sqc.academy.bai4.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class JsonResponse {

    public static <T> ResponseEntity<ApiResponse<T>> ok(T data) {
        return ResponseEntity.ok(
                ApiResponse.<T>builder()
                        .code(200)
                        .message("Success")
                        .data(data)
                        .build()
        );
    }

    public static <T> ResponseEntity<ApiResponse<T>> created(T data) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        ApiResponse.<T>builder()
                                .code(201)
                                .message("Created")
                                .data(data)
                                .build()
                );
    }

    public static ResponseEntity<ApiResponse<Void>> noContent() {
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(
                        ApiResponse.<Void>builder()
                                .code(204)
                                .message("No Content")
                                .build()
                );
    }
}
