package com.bank.trustbank.config;

import com.bank.trustbank.util.JwtTokenProvider;
import com.bank.trustbank.service.AuthService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthService authService;
    private final JwtTokenProvider jwtTokenProvider;

    public SecurityConfig(AuthService authService, JwtTokenProvider jwtTokenProvider) {
        this.authService = authService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        // Updated AuthenticationManager setup
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(authService)
                .passwordEncoder(passwordEncoder()); // Set password encoder

        return authenticationManagerBuilder.build();
    }

    protected void configure(HttpSecurity http) throws Exception {
        // Updated HttpSecurity configuration (Spring Security 6.1+)
        http
                .authorizeHttpRequests(authz ->
                        authz
                                .requestMatchers("/api/auth/login", "/api/auth/register").permitAll() // Allow login and register without authentication
                                .anyRequest().authenticated() // Require authentication for other requests
                )
                .csrf(csrf -> csrf.disable())  // Disable CSRF protection for APIs (updated method)
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
    }
}
