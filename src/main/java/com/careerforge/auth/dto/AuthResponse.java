package com.careerforge.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Authentication response")
public record AuthResponse(

        @Schema(
                description = "JWT Access Token",
                example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtYW5pQGV4YW1wbGUuY29tIn0.signature"
        )
        String token

) {
}