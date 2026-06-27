package com.careerforge.util;

import java.util.UUID;

import com.careerforge.application.dto.CreateApplicationRequest;
import com.careerforge.application.dto.UpdateApplicationStatusRequest;
import com.careerforge.application.entity.ApplicationStatus;
import com.careerforge.auth.dto.LoginRequest;
import com.careerforge.auth.dto.RegisterRequest;
import com.careerforge.opportunity.dto.CreateOpportunityRequest;

public final class TestDataFactory {

    private TestDataFactory() {
    }

    // ===========================
    // Authentication
    // ===========================

    public static RegisterRequest registerRequest() {

        return new RegisterRequest(
                "Mani Akhilesh Kumar",
                "mani@test.com",
                "Password@123"
        );

    }

    public static RegisterRequest duplicateRegisterRequest() {

        return new RegisterRequest(
                "Mani Akhilesh Kumar",
                "mani@test.com",
                "Password@123"
        );

    }

    public static LoginRequest loginRequest() {

        return new LoginRequest(
                "mani@test.com",
                "Password@123"
        );

    }

    public static LoginRequest invalidLoginRequest() {

        return new LoginRequest(
                "mani@test.com",
                "WrongPassword"
        );

    }

    // ===========================
    // Opportunity
    // ===========================

    public static CreateOpportunityRequest opportunityRequest() {

        return new CreateOpportunityRequest(
                "Software Engineer Intern",
                "Google",
                "Bengaluru",
                "₹50,000/month",
                "Spring Boot Backend Internship",
                "https://careers.google.com",
                "Internship"
        );

    }

    // ===========================
    // Application
    // ===========================

    public static CreateApplicationRequest applicationRequest(UUID opportunityId) {

        return new CreateApplicationRequest(opportunityId);

    }

    public static UpdateApplicationStatusRequest interviewScheduledStatus() {

        return new UpdateApplicationStatusRequest(
                ApplicationStatus.INTERVIEW_SCHEDULED
        );

    }

    public static UpdateApplicationStatusRequest rejectedStatus() {

        return new UpdateApplicationStatusRequest(
                ApplicationStatus.REJECTED
        );

    }

}