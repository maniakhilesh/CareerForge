package com.careerforge.opportunity.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.careerforge.opportunity.entity.Opportunity;

public interface OpportunityRepository
        extends JpaRepository<Opportunity, UUID> {
}