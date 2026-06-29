package com.careerforge.opportunity.specification;

import org.springframework.data.jpa.domain.Specification;

import com.careerforge.opportunity.entity.Opportunity;

public class OpportunitySpecification {

    private OpportunitySpecification() {
    }

    public static Specification<Opportunity> hasTitle(String title) {

        return (root, query, cb) ->
                title == null || title.isBlank()
                        ? cb.conjunction()
                        : cb.like(
                                cb.lower(root.get("title")),
                                "%" + title.toLowerCase() + "%"
                        );
    }

    public static Specification<Opportunity> hasCompany(String company) {

        return (root, query, cb) ->
                company == null || company.isBlank()
                        ? cb.conjunction()
                        : cb.like(
                                cb.lower(root.get("company")),
                                "%" + company.toLowerCase() + "%"
                        );
    }

    public static Specification<Opportunity> hasLocation(String location) {

        return (root, query, cb) ->
                location == null || location.isBlank()
                        ? cb.conjunction()
                        : cb.like(
                                cb.lower(root.get("location")),
                                "%" + location.toLowerCase() + "%"
                        );
    }

    public static Specification<Opportunity> hasEmploymentType(
            String employmentType
    ) {

        return (root, query, cb) ->
                employmentType == null || employmentType.isBlank()
                        ? cb.conjunction()
                        : cb.equal(
                                cb.lower(root.get("employmentType")),
                                employmentType.toLowerCase()
                        );
    }
}