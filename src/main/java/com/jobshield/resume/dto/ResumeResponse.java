package com.jobshield.resume.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class ResumeResponse {
    private Long id;
    private Long applicationProfileId;
    private String fileName;
    private String filePath;
    private Boolean isPrimary;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
