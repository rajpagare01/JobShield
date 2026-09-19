package com.jobshield.auth.service;

import com.jobshield.auth.dto.AuthResponse;
import com.jobshield.auth.dto.LoginRequest;
import com.jobshield.auth.dto.RegisterRequest;
import com.jobshield.security.JwtService;
import com.jobshield.user.entity.Role;
import com.jobshield.user.entity.Users;
import com.jobshield.user.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse register(RegisterRequest request) {

        if (userRepo.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        Users user = Users.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepo.save(user);

        String token = jwtService.generateToken(user.getEmail());

        return new AuthResponse(
                "User registered successfully",
                token
        );
    }

    public AuthResponse login(LoginRequest request) {

        Users user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Invalid email or password")
                );

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        )) {
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtService.generateToken(user.getEmail());

        return new AuthResponse(
                "User logged successfully",
                token
        );
    }
}