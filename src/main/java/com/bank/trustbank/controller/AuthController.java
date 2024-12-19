package com.bank.trustbank.controller;

import com.bank.trustbank.dto.LoginRequest;
import com.bank.trustbank.dto.LoginResponse;
import com.bank.trustbank.service.AuthService;
import com.bank.trustbank.util.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    // Endpoint for user login (authenticates the user)
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        // Validate user credentials and generate JWT token
        LoginResponse response = authService.authenticateUser(loginRequest);
        return ResponseEntity.ok(response);
    }

    // Endpoint for registering a new user (for demo purposes)
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody LoginRequest loginRequest) {
        authService.registerUser(loginRequest.getUsername(), loginRequest.getPassword());
        return ResponseEntity.ok("User registered successfully!");
    }
}
