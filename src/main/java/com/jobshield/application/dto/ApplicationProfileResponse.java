package com.jobshield.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApplicationProfileResponse {

    private Long id;
    private String name;
    private String description;
    private String domain;
    private Integer minimumMatchScore;
    private Boolean autoApplyEnabled;
}