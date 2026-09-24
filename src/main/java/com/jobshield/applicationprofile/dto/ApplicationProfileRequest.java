package com.jobshield.applicationprofile.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ApplicationProfileRequest {
    @NotBlank(message = "Profile name is Required")
    private String name;

    private String discription;
    @NotBlank(message = "Domain is required")
    private String domain;

    @NotNull(message = "Minimum match score is required")
    @Min(value = 0, message = "Minimum match score cannot be less than 0")
    @Max(value = 100, message = "Minimum match score cannot be greater than 100")
    private Integer minimumMatchScore;

    @NotNull(message = "Auto apply setting is required")
    private Boolean autoApplyEnabled;
}