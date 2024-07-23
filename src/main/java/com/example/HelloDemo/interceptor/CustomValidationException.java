package com.example.HelloDemo.interceptor;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.util.Set;

public class CustomValidationException extends RuntimeException {
    private Set<ConstraintViolation<?>> violations;
    

    public CustomValidationException(ConstraintViolationException e) {
        super(e);
        this.violations = e.getConstraintViolations();
    }

    public Set<ConstraintViolation<?>> getViolations() {
        return violations;
    }
}


