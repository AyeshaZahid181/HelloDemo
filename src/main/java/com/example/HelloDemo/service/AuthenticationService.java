package com.example.HelloDemo.service;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.HelloDemo.model.User;
import com.example.HelloDemo.repositories.UserRepository;

@Service
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private static final Logger log = LoggerFactory.getLogger(AuthenticationService.class);

    public AuthenticationService(UserRepository repository, PasswordEncoder passwordEncoder, JwtService jwtService,
                                 AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }
    //Register a User
    @Transactional(rollbackFor = Exception.class) // Rollback for any Exception
    public RegisterResponse register(User request) {
        try {
            log.debug("Starting user registration for username: {}", request.getUsername());

            // Validate the user object before saving
            log.debug("Validating user details: {}", request);

            // Create a new User entity
            User user = new User();
            user.setFullName(request.getFullName());
            user.setUsername(request.getUsername());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setRoles(request.getRoles());

            // Save the user entity to the database
            User savedUser = repository.save(user);
            log.debug("User saved successfully: {}", savedUser);

            // Generate JWT token for the registered user
            String token = jwtService.generateToken(savedUser);
            log.debug("JWT token generated for user: {}", savedUser.getUsername());

            // Return a RegisterResponse indicating successful registration
            return new RegisterResponse("User registered successfully.");
        } catch (Exception e) {
            log.error("Error during user registration: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to register user: " + e.getMessage(), e); // Provide more details
        }
    }

    public LoginResponse authenticate(User request) {
        log.debug("Starting authentication for username: {}", request.getUsername());

        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
            )
        );

        User user = repository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        String token = jwtService.generateToken(user);

        log.debug("Authentication successful for username: {}", request.getUsername());
        return new LoginResponse(token);
    }
}

