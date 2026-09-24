package com.jobshield.applicationprofile.service;

import com.jobshield.applicationprofile.dto.ApplicationProfileRequest;
import com.jobshield.applicationprofile.dto.ApplicationProfileResponse;
import com.jobshield.applicationprofile.entity.ApplicationProfile;
import com.jobshield.applicationprofile.repository.ApplicationProfileRepository;
import com.jobshield.candidate.entity.CandidateProfile;
import com.jobshield.candidate.repository.CandidateProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationProfileService {

    private final ApplicationProfileRepository applicationProfileRepository;
    private final CandidateProfileRepository candidateProfileRepository;

    public ApplicationProfileResponse createApplicationProfile(
            Long userId,
            ApplicationProfileRequest request) {

        CandidateProfile candidateProfile =
                candidateProfileRepository.findByUserId(userId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Candidate profile not found"
                                ));

        ApplicationProfile profile = ApplicationProfile.builder()
                .candidateProfile(candidateProfile)
                .name(request.getName())
                .description(request.getDiscription())
                .domain(request.getDomain())
                .minimumMatchScore(request.getMinimumMatchScore())
                .autoApplyEnabled(request.getAutoApplyEnabled())
                .build();

        return mapToResponse(
                applicationProfileRepository.save(profile)
        );
    }

    public List<ApplicationProfileResponse> getAllApplicationProfiles(
            Long userId) {

        CandidateProfile candidateProfile =
                candidateProfileRepository.findByUserId(userId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Candidate profile not found"
                                ));

        return applicationProfileRepository
                .findByCandidateProfileId(candidateProfile.getId())
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public ApplicationProfileResponse getApplicationProfile(
            Long userId,
            Long profileId) {

        CandidateProfile candidateProfile =
                candidateProfileRepository.findByUserId(userId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Candidate profile not found"
                                ));

        ApplicationProfile profile =
                applicationProfileRepository
                        .findByIdAndCandidateProfileId(
                                profileId,
                                candidateProfile.getId()
                        )
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Application profile not found"
                                ));

        return mapToResponse(profile);
    }

    public ApplicationProfileResponse updateApplicationProfile(
            Long userId,
            Long profileId,
            ApplicationProfileRequest request) {

        CandidateProfile candidateProfile =
                candidateProfileRepository.findByUserId(userId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Candidate profile not found"
                                ));

        ApplicationProfile profile =
                applicationProfileRepository
                        .findByIdAndCandidateProfileId(
                                profileId,
                                candidateProfile.getId()
                        )
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Application profile not found"
                                ));

        profile.setName(request.getName());
        profile.setDescription(request.getDiscription());
        profile.setDomain(request.getDomain());
        profile.setMinimumMatchScore(
                request.getMinimumMatchScore()
        );
        profile.setAutoApplyEnabled(
                request.getAutoApplyEnabled()
        );

        return mapToResponse(
                applicationProfileRepository.save(profile)
        );
    }

    public void deleteApplicationProfile(
            Long userId,
            Long profileId) {

        CandidateProfile candidateProfile =
                candidateProfileRepository.findByUserId(userId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Candidate profile not found"
                                ));

        ApplicationProfile profile =
                applicationProfileRepository
                        .findByIdAndCandidateProfileId(
                                profileId,
                                candidateProfile.getId()
                        )
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Application profile not found"
                                ));

        applicationProfileRepository.delete(profile);
    }

    private ApplicationProfileResponse mapToResponse(
            ApplicationProfile profile) {

        return new ApplicationProfileResponse(
                profile.getId(),
                profile.getName(),
                profile.getDescription(),
                profile.getDomain(),
                profile.getMinimumMatchScore(),
                profile.getAutoApplyEnabled()
        );
    }
}