package com.bank.trustbank.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {

    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Encrypt a plain text password using BCrypt
    public static String encryptPassword(String plainPassword) {
        return passwordEncoder.encode(plainPassword);
    }

    // Verify if a plain password matches the encrypted password
    public static boolean verifyPassword(String plainPassword, String encryptedPassword) {
        return passwordEncoder.matches(plainPassword, encryptedPassword);
    }
}
