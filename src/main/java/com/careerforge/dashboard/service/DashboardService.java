package com.careerforge.dashboard.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.careerforge.application.entity.ApplicationStatus;
import com.careerforge.application.repository.ApplicationRepository;
import com.careerforge.dashboard.dto.DashboardStatsResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final ApplicationRepository repository;

    public DashboardStatsResponse getStats(
            UUID userId
    ) {

        return new DashboardStatsResponse(

                repository.countByUserIdAndStatus(
                        userId,
                        ApplicationStatus.SAVED
                ),

                repository.countByUserIdAndStatus(
                        userId,
                        ApplicationStatus.APPLIED
                ),

                repository.countByUserIdAndStatus(
                        userId,
                        ApplicationStatus.INTERVIEW_SCHEDULED
                ),

                repository.countByUserIdAndStatus(
                        userId,
                        ApplicationStatus.OFFER_RECEIVED
                ),

                repository.countByUserIdAndStatus(
                        userId,
                        ApplicationStatus.REJECTED
                )
        );
    }
}