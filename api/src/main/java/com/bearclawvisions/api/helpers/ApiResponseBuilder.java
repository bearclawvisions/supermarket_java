package com.bearclawvisions.api.helpers;

import com.bearclawvisions.api.contracts.ApiResponse;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ApiResponseBuilder {

    /**
     * Creates a success response containing the specified message.
     *
     * @param <T>     The type of the data included in the response.
     * @param message The success message to include in the response.
     * @return A ResponseEntity containing a success ApiResponse with the specified message.
     */
    public static <T> @NonNull ResponseEntity<ApiResponse<T>> successResponse(HttpStatus status, String message) {
        ApiResponse<T> response = new ApiResponse.Builder<T>()
                .message(message)
                .build();

        return ResponseEntity.status(status).body(response);
    }

    /**
     * Creates a success response containing a message and data.
     *
     * @param <T>     The type of the data included in the response.
     * @param data    The data object to include in the response.
     * @return A ResponseEntity containing a success ApiResponse with the specified message and data.
     */
    public static <T> @NonNull ResponseEntity<ApiResponse<T>> successResponse(HttpStatus status, T data) {
        ApiResponse<T> response = new ApiResponse.Builder<T>()
                .data(data)
                .build();

        return ResponseEntity.status(status).body(response);
    }

    /**
     * Creates an error response with a custom HTTP status and a message.
     *
     * @param <T>     The type of the data in the ApiResponse.
     * @param status  The HTTP status to set in the response.
     * @param message The error message to include in the response.
     * @return A ResponseEntity containing an error ApiResponse with the specified
     *         HTTP status and message.
     */
    public static <T> @NonNull ResponseEntity<ApiResponse<T>> errorResponse(HttpStatus status, String message) {
        ApiResponse<T> response = new ApiResponse.Builder<T>()
                .message(message)
                .build();

        return ResponseEntity.status(status).body(response);
    }

    /**
     * Creates an error response with a custom HTTP status, an error message, and a list of errors.
     *
     * @param <T>     The type of the data in the ApiResponse.
     * @param status  The HTTP status to set in the response.
     * @param message The error message to include in the response.
     * @param errors  The list of error details to include in the response.
     * @return A ResponseEntity containing an error ApiResponse with the specified HTTP status,
     *         message, and list of errors.
     */
    public static <T> @NonNull ResponseEntity<ApiResponse<T>> errorResponse(HttpStatus status, String message, List<String> errors) {
        ApiResponse<T> response = new ApiResponse.Builder<T>()
                .message(message)
                .errors(errors)
                .build();

        return ResponseEntity.status(status).body(response);
    }
}
