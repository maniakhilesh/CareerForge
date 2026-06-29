package com.careerforge.opportunity.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.careerforge.opportunity.entity.Opportunity;

public interface OpportunityRepository
        extends JpaRepository<Opportunity, UUID>,
                JpaSpecificationExecutor<Opportunity> {

}