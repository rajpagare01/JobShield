package com.jobshield.candidate.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.aspectj.bridge.Message;

@Data
public class CandidateProfileRequest {

    @NotBlank(message ="Location is Required")
    private String location;

    private String about;

    @NotBlank(message ="Location is Required")
    private String education;

    private  String experience;
}
