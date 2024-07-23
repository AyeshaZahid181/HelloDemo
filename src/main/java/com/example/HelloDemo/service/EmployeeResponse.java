package com.example.HelloDemo.service;

import com.example.HelloDemo.model.Employee;

public class EmployeeResponse {
    private String message;
    private Employee employee;

    public EmployeeResponse(String message) {
        this.message = message;
    }

    public EmployeeResponse(String message, Employee employee) {
        this.message = message;
        this.employee = employee;
    }
 
    // Getters and Setters


	public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
