package com.careerforge.application.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.careerforge.application.entity.ApplicationStatus;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Application response")
public record ApplicationResponse(

        @Schema(example = "550e8400-e29b-41d4-a716-446655440000")
        UUID id,

        @Schema(example = "Software Engineer Intern")
        String opportunityTitle,

        @Schema(example = "Google")
        String company,

        @Schema(example = "APPLIED")
        ApplicationStatus status,

        @Schema(example = "2026-06-25T18:30:00")
        LocalDateTime appliedAt

) {
}