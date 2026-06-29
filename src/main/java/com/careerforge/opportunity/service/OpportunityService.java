package com.careerforge.opportunity.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.careerforge.opportunity.specification.OpportunitySpecification;
import com.careerforge.common.exception.OpportunityNotFoundException;
import com.careerforge.opportunity.dto.CreateOpportunityRequest;
import com.careerforge.opportunity.entity.Opportunity;
import com.careerforge.opportunity.repository.OpportunityRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OpportunityService {

    private final OpportunityRepository repository;

    public Opportunity create(
            CreateOpportunityRequest request
    ) {

        Opportunity opportunity =
                Opportunity.builder()
                        .title(request.title())
                        .company(request.company())
                        .location(request.location())
                        .salary(request.salary())
                        .description(request.description())
                        .sourceUrl(request.sourceUrl())
                        .employmentType(request.employmentType())
                        .build();

        return repository.save(opportunity);
    }

    public Page<Opportunity> findAll(
            Pageable pageable
    ) {

        return repository.findAll(pageable);

    }

    public Opportunity findById(UUID id) {

        return repository.findById(id)
                .orElseThrow(
                        OpportunityNotFoundException::new
                );

    }

    public void delete(UUID id) {

        Opportunity opportunity =
                repository.findById(id)
                        .orElseThrow(
                                OpportunityNotFoundException::new
                        );

        repository.delete(opportunity);

    }

    // ---------- SEARCH METHODS ----------
public Page<Opportunity> search(

        String title,
        String company,
        String location,
        String employmentType,
        Pageable pageable
) {

    Specification<Opportunity> specification =
            Specification.where(
                    OpportunitySpecification.hasTitle(title)
            )
            .and(
                    OpportunitySpecification.hasCompany(company)
            )
            .and(
                    OpportunitySpecification.hasLocation(location)
            )
            .and(
                    OpportunitySpecification.hasEmploymentType(
                            employmentType
                    )
            );

    return repository.findAll(
            specification,
            pageable
    );
}
}