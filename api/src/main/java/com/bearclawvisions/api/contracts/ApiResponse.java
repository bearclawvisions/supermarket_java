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

    /// Simple success response with `message`, `data`
    public ApiResponse(String message, T data) {
        this.message = message;
        this.data = data;
    }

    /// Simple success response with `message`
    public ApiResponse(String message) {
        this.message = message;
    }

    /// Simple failure response with `message`
    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    // Getters and Setters are needed for Jackson to do auto-magic creation of JSON
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
