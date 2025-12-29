package com.bearclawvisions.api.contracts;

import java.util.List;

public class ApiResponse<T> {
    private boolean success = true;
    private String message = "";
    private T data = null;
    private List<String> errors = null;

    // Java does not have a built-in default param like success = true, workaround is overloads or builder pattern

    /// Full response with `success`, `message`, `data`, and `errors`
    public ApiResponse(boolean success, String message, T data, List<String> errors) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.errors = errors;
    }

    public static class Builder<T> {
        private boolean success = false;
        private String message = "";
        private T data = null;
        private List<String> errors = null;

        public Builder<T> isSuccess() {
            this.success = true;
            return this;
        }

        public Builder<T> isError() {
            this.success = false;
            return this;
        }

        public Builder<T> message(String message) {
            this.message = message;
            return this;
        }

        public Builder<T> data(T data) {
            this.data = data;
            return this;
        }

        public Builder<T> errors(List<String> errors) {
            this.errors = errors;
            return this;
        }

        public ApiResponse<T> build() {
            return new ApiResponse<>(success, message, data, errors);
        }
    }

//    /// Simple success response with `message`, `data`
//    public ApiResponse(String message, T data) {
//        this.message = message;
//        this.data = data;
//    }
//
//    /// Simple success response with `message`
//    public ApiResponse(String message) {
//        this.message = message;
//    }
//
//    /// Simple failure response with `message`
//    public ApiResponse(boolean success, String message) {
//        this.success = success;
//        this.message = message;
//    }

    // Getters are needed for Jackson to do auto-magic creation of JSON
    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public List<String> getErrors() {
        return errors;
    }
}
