package com.bearclawvisions.api.contracts;

import java.util.List;

public class ApiResponse<T> {

    private String message = "";
    private T data = null;
    private List<String> errors = null;

    public ApiResponse(String message, T data, List<String> errors) {
        this.message = message;
        this.data = data;
        this.errors = errors;
    }

    public static class Builder<T> {
        private String message = "";
        private T data = null;
        private List<String> errors = null;

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
            return new ApiResponse<>(message, data, errors);
        }
    }

    // Getters are needed for Jackson to do auto-magic creation of JSON
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
