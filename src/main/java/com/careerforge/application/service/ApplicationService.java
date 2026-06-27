package com.careerforge.application.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.careerforge.application.dto.ApplicationResponse;
import com.careerforge.application.dto.CreateApplicationRequest;
import com.careerforge.application.dto.UpdateApplicationStatusRequest;
import com.careerforge.application.entity.Application;
import com.careerforge.application.entity.ApplicationStatus;
import com.careerforge.application.repository.ApplicationRepository;
import com.careerforge.common.exception.ApplicationNotFoundException;
import com.careerforge.common.exception.DuplicateApplicationException;
import com.careerforge.common.exception.OpportunityNotFoundException;
import com.careerforge.common.exception.UserNotFoundException;
import com.careerforge.opportunity.entity.Opportunity;
import com.careerforge.opportunity.repository.OpportunityRepository;
import com.careerforge.user.entity.User;
import com.careerforge.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final OpportunityRepository opportunityRepository;
    private final UserRepository userRepository;

    public ApplicationResponse create(
            UUID userId,
            CreateApplicationRequest request
    ) {

        User user = userRepository
                .findById(userId)
                .orElseThrow(UserNotFoundException::new);

        Opportunity opportunity =
                opportunityRepository
                        .findById(
                                request.opportunityId()
                        )
                        .orElseThrow(OpportunityNotFoundException::new);
        if (applicationRepository
        .existsByUserAndOpportunity(
                user,
                opportunity
        )) {

    throw new DuplicateApplicationException();
}

        Application application =
                Application.builder()
                        .user(user)
                        .opportunity(opportunity)
                        .status(ApplicationStatus.APPLIED)
                        .appliedAt(LocalDateTime.now())
                        .build();

        return toResponse(
        applicationRepository.save(application)
);
    }

    public List<ApplicationResponse> findMyApplications(
            UUID userId
    ) {

        User user = userRepository
                .findById(userId)
                .orElseThrow(UserNotFoundException::new);

        return applicationRepository
        .findByUser(user)
        .stream()
        .map(this::toResponse)
        .toList();
 }
private ApplicationResponse toResponse(
        Application application
) {

    return new ApplicationResponse(
            application.getId(),
            application.getOpportunity().getTitle(),
            application.getOpportunity().getCompany(),
            application.getStatus(),
            application.getAppliedAt()
    );
}
public ApplicationResponse updateStatus(
        UUID applicationId,
        UUID userId,
        UpdateApplicationStatusRequest request
) {

    Application application =
            applicationRepository
                    .findById(applicationId)
                    .orElseThrow(ApplicationNotFoundException::new);

    if (!application.getUser().getId().equals(userId)) {
        throw new RuntimeException(
                "You cannot modify another user's application"
        );
    }

    application.setStatus(
            request.status()
    );

    Application saved =
            applicationRepository.save(application);

    return toResponse(saved);
        }
}