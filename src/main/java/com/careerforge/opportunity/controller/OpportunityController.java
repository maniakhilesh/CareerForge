package com.careerforge.opportunity.controller;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.careerforge.opportunity.dto.CreateOpportunityRequest;
import com.careerforge.opportunity.dto.OpportunityResponse;
import com.careerforge.opportunity.entity.Opportunity;
import com.careerforge.opportunity.mapper.OpportunityMapper;
import com.careerforge.opportunity.service.OpportunityService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(
        name = "Opportunities",
        description = "Job and Internship Opportunity APIs"
)
@RestController
@RequestMapping("/api/opportunities")
@RequiredArgsConstructor
public class OpportunityController {

    private final OpportunityService service;
    private final OpportunityMapper mapper;

    @Operation(
            summary = "Create Opportunity",
            description = "Creates a new job or internship opportunity."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Opportunity created successfully"),
            @ApiResponse(responseCode = "400", description = "Validation failed"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PostMapping
    public ResponseEntity<OpportunityResponse> create(
            @Valid @RequestBody CreateOpportunityRequest request
    ) {

        Opportunity opportunity = service.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.toResponse(opportunity));
    }

    @Operation(
            summary = "Search & Filter Opportunities",
            description = "Returns opportunities with optional filtering and pagination."
    )
    @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Opportunities retrieved successfully"),
    @ApiResponse(responseCode = "400", description = "Invalid request parameters")
})
    @GetMapping
    public ResponseEntity<Page<OpportunityResponse>> search(

            @RequestParam(required = false) String title,
            @RequestParam(required = false) String company,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String employmentType,

            Pageable pageable
    ) {

        Page<OpportunityResponse> response =
                service.search(
                        title,
                        company,
                        location,
                        employmentType,
                        pageable
                ).map(mapper::toResponse);

        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Get Opportunity",
            description = "Returns an opportunity by its ID."
    )
    @GetMapping("/{id}")
    public ResponseEntity<OpportunityResponse> findById(
            @PathVariable UUID id
    ) {

        return ResponseEntity.ok(
                mapper.toResponse(
                        service.findById(id)
                )
        );
    }

    @Operation(
            summary = "Delete Opportunity",
            description = "Deletes an opportunity."
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable UUID id
    ) {

        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}