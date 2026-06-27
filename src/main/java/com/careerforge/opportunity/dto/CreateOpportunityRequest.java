package com.careerforge.opportunity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Request payload for creating a job or internship opportunity")
public record CreateOpportunityRequest(

        @Schema(
                description = "Job title",
                example = "Software Engineer Intern"
        )
        @NotBlank
        String title,

        @Schema(
                description = "Company name",
                example = "Google"
        )
        @NotBlank
        String company,

        @Schema(
                description = "Job location",
                example = "Bengaluru"
        )
        String location,

        @Schema(
                description = "Salary or stipend",
                example = "₹50,000/month"
        )
        String salary,

        @Schema(
                description = "Detailed description",
                example = "Work on backend microservices using Spring Boot."
        )
        String description,

        @Schema(
                description = "Original job posting URL",
                example = "https://careers.google.com/jobs/12345"
        )
        String sourceUrl,

        @Schema(
                description = "Employment type",
                example = "Internship"
        )
        String employmentType

) {
}