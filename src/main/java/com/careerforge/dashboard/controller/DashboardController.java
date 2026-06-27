package com.careerforge.dashboard.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.careerforge.auth.security.UserPrincipal;
import com.careerforge.dashboard.dto.DashboardStatsResponse;
import com.careerforge.dashboard.service.DashboardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService service;

    @GetMapping("/stats")
    public ResponseEntity<DashboardStatsResponse> stats(
            @AuthenticationPrincipal UserPrincipal user
    ) {

        return ResponseEntity.ok(
                service.getStats(user.getId())
        );
    }
}