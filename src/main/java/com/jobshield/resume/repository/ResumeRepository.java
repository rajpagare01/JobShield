package com.jobshield.resume.repository;

import com.jobshield.resume.dto.ResumeRequest;
import com.jobshield.resume.dto.ResumeResponse;
import com.jobshield.resume.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ResumeRepository  extends JpaRepository<Resume, Integer> {
    List<Resume> findByApplicationProfileId(Long applicationProfileId);

    Optional<Resume> findByIdAndApplicationProfileId(
            Long id,
            Long applicationProfileId
    );


    public ResumeResponse createResume(
            Long userId,
            Long applicationProfileId,
            ResumeRequest request
    )




}
