package com.careerforge.application.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.careerforge.application.dto.ApplicationResponse;
import com.careerforge.application.dto.CreateApplicationRequest;
import com.careerforge.application.dto.UpdateApplicationStatusRequest;
import com.careerforge.application.service.ApplicationService;
import com.careerforge.auth.security.UserPrincipal;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(
        name = "Applications",
        description = "Application tracking APIs"
)
@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService service;

    @Operation(
            summary = "Create Application",
            description = "Creates a new application for an opportunity."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Application created successfully"),
            @ApiResponse(responseCode = "400", description = "Validation failed"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Opportunity not found")
    })
    @PostMapping
    public ResponseEntity<ApplicationResponse> create(
            @AuthenticationPrincipal UserPrincipal user,
            @Valid @RequestBody CreateApplicationRequest request
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        service.create(
                                user.getId(),
                                request
                        )
                );
    }

    @Operation(
            summary = "My Applications",
            description = "Returns all applications of the authenticated user."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Applications retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping("/my")
    public ResponseEntity<List<ApplicationResponse>> myApplications(
            @AuthenticationPrincipal UserPrincipal user
    ) {

        return ResponseEntity.ok(
                service.findMyApplications(
                        user.getId()
                )
        );
    }

    @Operation(
            summary = "Update Application Status",
            description = "Updates the status of an existing application."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Status updated successfully"),
            @ApiResponse(responseCode = "400", description = "Validation failed"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Application not found")
    })
    @PatchMapping("/{id}/status")
    public ResponseEntity<ApplicationResponse> updateStatus(

            @PathVariable UUID id,

            @AuthenticationPrincipal UserPrincipal user,

            @Valid
            @RequestBody
            UpdateApplicationStatusRequest request
    ) {

        return ResponseEntity.ok(
                service.updateStatus(
                        id,
                        user.getId(),
                        request
                )
        );
    }
}