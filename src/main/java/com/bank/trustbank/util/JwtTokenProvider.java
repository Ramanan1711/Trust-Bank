package com.bank.trustbank.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    private static final long JWT_EXPIRATION = 86400000; // 1 day in milliseconds

    // Generate a JWT token for the provided username
    public String createToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    // Extract username from the token
    public String getUsernameFromToken(String token) {
        JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(jwtSecret).build();
        Claims claims = jwtParser.parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    // Validate the token to ensure it is correctly signed and not expired
    public boolean validateToken(String token) {
        try {
            JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(jwtSecret).build();
            jwtParser.parseClaimsJws(token); // If this succeeds, the token is valid
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
