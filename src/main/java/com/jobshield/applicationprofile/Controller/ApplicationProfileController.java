package com.jobshield.applicationprofile.Controller;

import com.jobshield.applicationprofile.dto.ApplicationProfileRequest;
import com.jobshield.applicationprofile.dto.ApplicationProfileResponse;
import com.jobshield.applicationprofile.service.ApplicationProfileService;
import com.jobshield.user.entity.Users;
import com.jobshield.user.repository.UserRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/application-profiles")
public class ApplicationProfileController {
    private final ApplicationProfileService applicationProfileService;
    private final UserRepo userRepo;

    @PostMapping
    public ResponseEntity<ApplicationProfileResponse> createProfile(
            @Valid @RequestBody ApplicationProfileRequest request,
            Authentication authentication) {

        Users user = getAuthenticatedUser(authentication);

        ApplicationProfileResponse response =
                applicationProfileService.createApplicationProfile(
                        user.getId(),
                        request
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<ApplicationProfileResponse>> getAllProfiles(
            Authentication authentication) {

        Users user = getAuthenticatedUser(authentication);

        return ResponseEntity.ok(
                applicationProfileService
                        .getAllApplicationProfiles(user.getId())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationProfileResponse> getProfile(
            @PathVariable Long id,
            Authentication authentication) {

        Users user = getAuthenticatedUser(authentication);

        return ResponseEntity.ok(
                applicationProfileService
                        .getApplicationProfile(
                                user.getId(),
                                id
                        )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApplicationProfileResponse> updateProfile(
            @PathVariable Long id,
            @Valid @RequestBody ApplicationProfileRequest request,
            Authentication authentication) {

        Users user = getAuthenticatedUser(authentication);

        return ResponseEntity.ok(
                applicationProfileService
                        .updateApplicationProfile(
                                user.getId(),
                                id,
                                request
                        )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfile(
            @PathVariable Long id,
            Authentication authentication) {

        Users user = getAuthenticatedUser(authentication);

        applicationProfileService.deleteApplicationProfile(
                user.getId(),
                id
        );

        return ResponseEntity.noContent().build();
    }

    private Users getAuthenticatedUser(
            Authentication authentication) {

        String email = authentication.getName();

        return userRepo.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
    }
}