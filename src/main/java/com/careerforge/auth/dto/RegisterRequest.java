package com.careerforge.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "User registration request")
public record RegisterRequest(

        @Schema(
                description = "Full name",
                example = "Mani Akhilesh Kumar"
        )
        @NotBlank
        @Size(min = 2, max = 100)
        String name,

        @Schema(
                description = "Email address",
                example = "mani@example.com"
        )
        @Email
        @NotBlank
        String email,

        @Schema(
                description = "Password",
                example = "Password@123"
        )
        @NotBlank
        @Size(min = 8, max = 100)
        String password

) {
}