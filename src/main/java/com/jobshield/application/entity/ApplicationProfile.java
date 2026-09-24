package com.jobshield.application.entity;

import com.jobshield.candidate.entity.CandidateProfile;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "application_profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "candidate_profile_id",
            nullable = false
    )
    private CandidateProfile candidateProfile;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, length = 100)
    private String domain;

    @Column(nullable = false)
    private Integer minimumMatchScore;

    @Column(nullable = false)
    private Boolean autoApplyEnabled;
}