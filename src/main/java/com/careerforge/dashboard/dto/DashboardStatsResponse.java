package com.careerforge.dashboard.dto;

public record DashboardStatsResponse(

        long saved,
        long applied,
        long interviewScheduled,
        long offersReceived,
        long rejected

) {}