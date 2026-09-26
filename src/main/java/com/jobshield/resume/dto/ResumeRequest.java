package com.jobshield.resume.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ResumeRequest {

    @NotBlank(message = "File name is required")
    private String fileName;

    @NotBlank(message = "File path is required")
    private String filePath;

    private Boolean isPrimary;
}