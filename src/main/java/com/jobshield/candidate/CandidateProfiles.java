package com.jobshield.candidate;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity

public class CandidateProfiles {
    @Id
    private Long id;
    private Long userId;
    private String education;
    private String experience;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String about;
    private String location;
}

