package com.example.HelloDemo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import com.example.HelloDemo.model.User;
import com.example.HelloDemo.service.AuthenticationService;
import com.example.HelloDemo.service.LoginResponse;
import com.example.HelloDemo.service.RegisterResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping
public class AuthenticationController {

    private final AuthenticationService authService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authService = authenticationService;
    }
     
    //Register a User
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> registerUser(@Valid @RequestBody User user) {
        try {
            RegisterResponse response = authService.register(user);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RegisterResponse(e.getMessage()));
        }
    }
    
     //Login a user 
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody User request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
}
