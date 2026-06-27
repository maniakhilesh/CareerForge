package com.careerforge.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Login request payload")
public record LoginRequest(

        @Schema(
                description = "Registered email address",
                example = "john@example.com"
        )
        @Email
        @NotBlank
        String email,

        @Schema(
                description = "User password",
                example = "Password@123"
        )
        @NotBlank
        String password

) {
}