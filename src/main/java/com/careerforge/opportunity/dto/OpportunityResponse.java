package com.careerforge.opportunity.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.careerforge.opportunity.entity.Opportunity;

public record OpportunityResponse(

        UUID id,
        String title,
        String company,
        String location,
        String salary,
        String description,
        String sourceUrl,
        String employmentType,
        LocalDateTime createdAt

) {

    public static OpportunityResponse from(Opportunity opportunity) {

        return new OpportunityResponse(
                opportunity.getId(),
                opportunity.getTitle(),
                opportunity.getCompany(),
                opportunity.getLocation(),
                opportunity.getSalary(),
                opportunity.getDescription(),
                opportunity.getSourceUrl(),
                opportunity.getEmploymentType(),
                opportunity.getCreatedAt()
        );
    }
}