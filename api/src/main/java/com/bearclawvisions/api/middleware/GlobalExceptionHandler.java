package com.bearclawvisions.api.middleware;

import com.bearclawvisions.api.contracts.ApiResponse;
import com.bearclawvisions.api.helpers.ApiResponseBuilder;
import com.bearclawvisions.exceptions.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice(basePackages = "com.bearclawvisions.api.controllers")
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<String>> handleBusinessException(BusinessException e) {
        return ApiResponseBuilder.error(HttpStatus.BAD_REQUEST,"Business Error", List.of(e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleGenericException(Exception e) {
        return ApiResponseBuilder.error(HttpStatus.BAD_REQUEST, "Internal Server Error", List.of(e.getMessage()));
    }

    @ExceptionHandler(SecurityException.class)
    public ResponseEntity<ApiResponse<String>> handleSecurityException(SecurityException e) {
        return ApiResponseBuilder.error(HttpStatus.UNAUTHORIZED, "Unauthorized", List.of(e.getMessage()));
    }
}
