package com.bearclawvisions.api.helpers;

import com.bearclawvisions.api.contracts.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ApiResponseBuilder {

    /// Success response with only a message for business logic success
    public static <T> ResponseEntity<ApiResponse<T>> success(String message) {
        ApiResponse<T> response = new ApiResponse.Builder<T>()
                .isSuccess()
                .message(message)
                .build();

        return ResponseEntity.ok(response);
    }

    /// Success response with data
    public static <T> ResponseEntity<ApiResponse<T>> success(T data) {
        ApiResponse<T> response = new ApiResponse.Builder<T>()
                .isSuccess()
                .message("Success")
                .data(data)
                .build();

        return ResponseEntity.ok(response);
    }

    // Success response with a custom message and data
    public static <T> ResponseEntity<ApiResponse<T>> success(String message, T data) {
        ApiResponse<T> response = new ApiResponse.Builder<T>()
                .isSuccess()
                .message(message)
                .data(data)
                .build();

        return ResponseEntity.ok(response);
    }

    ///  Simple error response with message for business logic errors
    public static <T> ResponseEntity<ApiResponse<T>> error(String message) {
        ApiResponse<T> response = new ApiResponse.Builder<T>()
                .isError()
                .message(message)
                .build();

        return ResponseEntity.ok(response);
    }

    ///  Error response with custom HttpStatus and message
    public static <T> ResponseEntity<ApiResponse<T>> error(HttpStatus status, String message) {
        ApiResponse<T> response = new ApiResponse.Builder<T>()
                .isError()
                .message(message)
                .build();

        return ResponseEntity.status(status).body(response);
    }

    ///  Error response with custom HttpStatus, message, and errors
    public static <T> ResponseEntity<ApiResponse<T>> error(HttpStatus status, String message, List<String> errors) {
        ApiResponse<T> response = new ApiResponse.Builder<T>()
                .isError()
                .message(message)
                .errors(errors)
                .build();

        return ResponseEntity.status(status).body(response);
    }
}
