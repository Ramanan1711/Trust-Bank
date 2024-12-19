package com.bank.trustbank.service;

import com.bank.trustbank.dto.LoginRequest;
import com.bank.trustbank.dto.LoginResponse;
import com.bank.trustbank.model.User;
import com.bank.trustbank.repository.UserRepository;
import com.bank.trustbank.util.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;


import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Authenticate user and generate JWT token
    public LoginResponse authenticateUser(LoginRequest loginRequest) {
        Optional<User> userOptional = userRepository.findByUsername(loginRequest.getUsername());

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Verify password
            if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                String token = jwtTokenProvider.createToken(user.getUsername());
                return new LoginResponse(token);
            } else {
                throw new BadCredentialsException("Invalid credentials.");
            }
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
    }

    // Register a new user (for demo purposes)
    public void registerUser(String username, String password) {
        // Check if the user already exists
        Optional<User> existingUser = userRepository.findByUsername(username);
        if (existingUser.isPresent()) {
            throw new RuntimeException("User already exists.");
        }

        // Hash the password before saving
        String hashedPassword = passwordEncoder.encode(password);

        // Create a new user entity and save to the database
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(hashedPassword);
        userRepository.save(newUser);
    }

    // Implementing the loadUserByUsername method for UserDetailsService
    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getUsername())
                    .password(user.getPassword())
                    .roles("USER") // You can assign more roles if needed
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
    }
}
