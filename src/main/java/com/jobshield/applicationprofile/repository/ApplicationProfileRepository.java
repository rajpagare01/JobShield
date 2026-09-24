package com.jobshield.applicationprofile.repository;


import com.jobshield.applicationprofile.entity.ApplicationProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationProfileRepository extends JpaRepository<ApplicationProfile, Long> {

    List<ApplicationProfile> findByCandidateProfileId(Long candidateProfileId);

    Optional<ApplicationProfile> findByIdAndCandidateProfileId(Long id , Long candidateProfileId);
}
