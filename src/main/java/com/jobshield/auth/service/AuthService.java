package com.jobshield.auth.service;

import com.jobshield.auth.dto.AuthResponse;
import com.jobshield.auth.dto.RegisterRequest;
import com.jobshield.user.entity.Role;
import com.jobshield.user.entity.Users;
import com.jobshield.user.repository.UserRepo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;
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

        return new AuthResponse("User registered successfully");
    }
}
