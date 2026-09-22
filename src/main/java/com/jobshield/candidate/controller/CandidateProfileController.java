package com.jobshield.candidate.controller;

import com.jobshield.candidate.dto.CandidateProfileRequest;
import com.jobshield.candidate.dto.CandidateProfileResponse;
import com.jobshield.candidate.entity.CandidateProfile;

import com.jobshield.candidate.repository.CandidateProfileRepository;
import com.jobshield.candidate.service.CandidateProfileService;
import com.jobshield.user.entity.Users;
import com.jobshield.user.repository.UserRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/candidate")
public class CandidateProfileController {
    private final CandidateProfileService candidateProfileService;
    private final UserRepo userRepo;

    @PostMapping("/profile")
    public ResponseEntity<CandidateProfile> createProfile(
            @Valid @RequestBody CandidateProfileRequest request,
            Authentication authentication) {

        System.out.println("===== CANDIDATE CONTROLLER =====");
        System.out.println("Authenticated user: " + authentication.getName());

        String email = authentication.getName();

        // rest of your code...
        Users users = userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("user not found"));
        CandidateProfile profile = candidateProfileService.createCandidateProfile(users.getId(), request);
        return ResponseEntity.status(HttpStatus.CREATED).body(profile);
    }

    @GetMapping("/profile")
    public ResponseEntity<CandidateProfileResponse> getProfile(Authentication authentication) {
        String email = authentication.getName();
        System.out.println("email is "+email);
        Users users = userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("user not found"));
        System.out.println("user "+users);
        CandidateProfileResponse profile =  candidateProfileService.getCandidateProfile(users.getId());
        return ResponseEntity.ok(profile);
    }
}