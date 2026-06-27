package com.careerforge.application.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.careerforge.application.entity.Application;
import com.careerforge.opportunity.entity.Opportunity;
import com.careerforge.user.entity.User;

public interface ApplicationRepository
        extends JpaRepository<Application, UUID> {

    List<Application> findByUser(User user);
    boolean existsByUserAndOpportunity(
        User user,
        Opportunity opportunity
);
    long countByUserIdAndStatus(
        java.util.UUID userId,
        com.careerforge.application.entity.ApplicationStatus status
);
}