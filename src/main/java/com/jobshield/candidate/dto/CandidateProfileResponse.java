package com.jobshield.candidate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CandidateProfileResponse {
    private Long Id;
    private String name;
    private String email;
    private String location;
    private String about;
    private String education;
    private String Experience;
}
