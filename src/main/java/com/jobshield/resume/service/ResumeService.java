package com.jobshield.resume.service;

import com.jobshield.applicationprofile.repository.ApplicationProfileRepository;
import com.jobshield.candidate.entity.CandidateProfile;
import com.jobshield.candidate.repository.CandidateProfileRepository;
import com.jobshield.resume.repository.ResumeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ResumeService {

    private final ResumeRepository resumeRepository;
    private final ApplicationProfileRepository applicationProfileRepository;
    private final CandidateProfileRepository candidateProfileRepository;





}
