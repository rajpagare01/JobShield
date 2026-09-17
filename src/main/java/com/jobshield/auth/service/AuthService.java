package com.jobshield.auth.service;

import com.jobshield.auth.dto.AuthResponse;
import com.jobshield.auth.dto.RegisterRequest;
import com.jobshield.user.entity.Users;
import com.jobshield.user.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    public AuthResponse register(@RequestBody RegisterRequest registerRequest) {
        if(userRepo.existsByEmail(registerRequest.getEmail())){
            throw new RuntimeException("Email already exists");
        }

        Users user = Users.builder()
                .name(registerRequest.getName())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .email(registerRequest.getEmail())
                .build();
        userRepo.save(user);
        return new AuthResponse("User registered successfully");
    }
}
