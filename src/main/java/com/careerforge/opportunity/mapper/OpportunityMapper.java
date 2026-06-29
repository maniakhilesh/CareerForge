package com.careerforge.opportunity.mapper;

import org.springframework.stereotype.Component;

import com.careerforge.opportunity.dto.OpportunityResponse;
import com.careerforge.opportunity.entity.Opportunity;

@Component
public class OpportunityMapper {

    public OpportunityResponse toResponse(Opportunity opportunity) {

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