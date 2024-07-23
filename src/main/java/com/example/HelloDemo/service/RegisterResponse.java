package com.example.HelloDemo.service;

import java.util.List;

public class RegisterResponse {
    private String message;
    private List<String> errors; // Add this field for error messages

    // Constructor for success response
    public RegisterResponse(String message) {
        this.message = message;
    }

    // Constructor for validation error response
    public RegisterResponse(String message, List<String> errors) {
        this.message = message;
        this.errors = errors;
    }

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
