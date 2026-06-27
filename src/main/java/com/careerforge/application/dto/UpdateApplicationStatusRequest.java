package com.careerforge.application.dto;

import com.careerforge.application.entity.ApplicationStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Request payload for updating application status")
public record UpdateApplicationStatusRequest(

        @Schema(
                description = "New application status",
                example = "INTERVIEW_SCHEDULED"
        )
        @NotNull
        ApplicationStatus status

) {
}