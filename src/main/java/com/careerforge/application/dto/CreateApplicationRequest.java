package com.careerforge.application.dto;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Request payload for creating an application")
public record CreateApplicationRequest(

        @Schema(
                description = "Opportunity ID",
                example = "550e8400-e29b-41d4-a716-446655440000"
        )
        @JsonProperty("opportunityId")
        @NotNull
        UUID opportunityId

) {
}