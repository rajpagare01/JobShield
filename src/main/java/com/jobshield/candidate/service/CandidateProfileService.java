package com.jobshield.candidate.service;

import com.jobshield.candidate.dto.CandidateProfileRequest;
import com.jobshield.candidate.dto.CandidateProfileResponse;
import com.jobshield.candidate.entity.CandidateProfile;
import com.jobshield.candidate.repository.CandidateProfileRepository;
import com.jobshield.user.entity.Users;
import com.jobshield.user.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CandidateProfileService {
    private final CandidateProfileRepository candidateProfileRepository;
    private final UserRepo userRepo;

    public CandidateProfile createCandidateProfile(Long userId, CandidateProfileRequest request) {

        if (candidateProfileRepository.existsByUserId(userId)) {
            //return candidateProfileRepository.findById(userId).get();
            throw new RuntimeException("Candidate profile already exists");

        }
        Users user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        CandidateProfile profile = CandidateProfile.builder()
                .user(user)
                .location(request.getLocation())
                .about(request.getAbout())
                .education(request.getEducation())
                .experience(request.getExperience()).build();
        return candidateProfileRepository.save(profile);
    }

    public CandidateProfileResponse getCandidateProfile(Long userId) {
        System.out.println(userId+"user id is");
        CandidateProfile profile = candidateProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Candidate profile not found"));
        Users user = profile.getUser();
        return new CandidateProfileResponse(
                profile.getId(),
                user.getName(),
                user.getEmail(),
                profile.getLocation(),
                profile.getAbout(),
                profile.getEducation(),
                profile.getExperience()
        );
    }

    public CandidateProfileResponse updateCandidateProfile(Long userId, CandidateProfileRequest candidateProfileRequest){
        CandidateProfile candidateProfile = candidateProfileRepository.findByUserId(userId)
                .orElseThrow(()->new RuntimeException("candidate not found"));
        candidateProfile.setLocation(candidateProfileRequest.getLocation());
        candidateProfile.setAbout(candidateProfileRequest.getAbout());
        candidateProfileRequest.setEducation(candidateProfileRequest.getEducation());
        candidateProfileRequest.setExperience(candidateProfileRequest.getExperience());
        CandidateProfile updatedProfile =
                candidateProfileRepository.save(candidateProfile);

        Users user = candidateProfile.getUser();

        return new CandidateProfileResponse(
                updatedProfile.getId(),
                user.getName(),
                user.getEmail(),
                updatedProfile.getLocation(),
                updatedProfile.getAbout(),
                updatedProfile.getEducation(),
                updatedProfile.getExperience()
        );
    }
    public void deleteCandidateProfile(Long userId) {

        CandidateProfile profile =
                candidateProfileRepository.findByUserId(userId)
                        .orElseThrow(() ->
                                new RuntimeException("Candidate profile not found")
                        );

        candidateProfileRepository.delete(profile);
    }
}
