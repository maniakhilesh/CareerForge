package com.careerforge.application;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.careerforge.IntegrationTestConfig;
import com.careerforge.application.dto.CreateApplicationRequest;
import com.careerforge.application.dto.UpdateApplicationStatusRequest;
import com.careerforge.application.entity.ApplicationStatus;
import com.careerforge.opportunity.entity.Opportunity;
import com.careerforge.user.entity.User;
import com.careerforge.util.JsonUtil;

class ApplicationControllerIT extends IntegrationTestConfig {

    private String bearerToken;
    private User user;
    private Opportunity opportunity;

    @BeforeEach
    void setup() {

        applicationRepository.deleteAll();
        opportunityRepository.deleteAll();
        userRepository.deleteAll();

        user = authHelper.createTestUser();

        bearerToken = authHelper.bearerToken();

        opportunity = opportunityRepository.save(

                Opportunity.builder()
                        .title("Software Engineer Intern")
                        .company("Google")
                        .location("Bengaluru")
                        .salary("50000")
                        .description("Spring Boot Internship")
                        .employmentType("Internship")
                        .build()

        );

    }

    @Test
    void shouldCreateApplication() throws Exception {

        CreateApplicationRequest request =
                new CreateApplicationRequest(
                        opportunity.getId()
                );

        mockMvc.perform(

                post("/api/applications")
                        .header("Authorization", bearerToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(request))

        )
        .andExpect(status().isCreated());

    }

    @Test
    void shouldRejectDuplicateApplication() throws Exception {

        CreateApplicationRequest request =
                new CreateApplicationRequest(
                        opportunity.getId()
                );

        mockMvc.perform(

                post("/api/applications")
                        .header("Authorization", bearerToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(request))

        );

        mockMvc.perform(

                post("/api/applications")
                        .header("Authorization", bearerToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(request))

        )
        .andExpect(status().isConflict());

    }

    @Test
    void shouldReturnMyApplications() throws Exception {

        CreateApplicationRequest request =
                new CreateApplicationRequest(
                        opportunity.getId()
                );

        mockMvc.perform(

                post("/api/applications")
                        .header("Authorization", bearerToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(request))

        );

        mockMvc.perform(

                get("/api/applications/my")
                        .header("Authorization", bearerToken)

        )
        .andExpect(status().isOk());

    }
        @Test
    void shouldUpdateApplicationStatus() throws Exception {

        CreateApplicationRequest createRequest =
                new CreateApplicationRequest(opportunity.getId());

        String response = mockMvc.perform(

                post("/api/applications")
                        .header("Authorization", bearerToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(createRequest))

        ).andReturn()
         .getResponse()
         .getContentAsString();

        UUID applicationId =
                objectMapper.readTree(response)
                        .get("id")
                        .traverse()
                        .readValueAs(UUID.class);

        UpdateApplicationStatusRequest request =
                new UpdateApplicationStatusRequest(
                        ApplicationStatus.INTERVIEW_SCHEDULED
                );

        mockMvc.perform(

                patch("/api/applications/" + applicationId + "/status")
                        .header("Authorization", bearerToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(request))

        )
        .andExpect(status().isOk());

    }

    @Test
    void shouldReturn404ForUnknownApplication() throws Exception {

        UpdateApplicationStatusRequest request =
                new UpdateApplicationStatusRequest(
                        ApplicationStatus.INTERVIEW_COMPLETED
                );

        mockMvc.perform(

                patch("/api/applications/" + UUID.randomUUID() + "/status")
                        .header("Authorization", bearerToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(request))

        )
        .andExpect(status().isNotFound());

    }

    @Test
    void shouldReturn404ForUnknownOpportunity() throws Exception {

        CreateApplicationRequest request =
                new CreateApplicationRequest(
                        UUID.randomUUID()
                );

        mockMvc.perform(

                post("/api/applications")
                        .header("Authorization", bearerToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(request))

        )
        .andExpect(status().isNotFound());

    }

    @Test
    void shouldRejectRequestWithoutJwt() throws Exception {

        CreateApplicationRequest request =
                new CreateApplicationRequest(
                        opportunity.getId()
                );

        mockMvc.perform(

                post("/api/applications")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(request))

        )
        .andExpect(status().isUnauthorized());

    }

    @Test
    void shouldRejectInvalidStatus() throws Exception {

        CreateApplicationRequest createRequest =
                new CreateApplicationRequest(
                        opportunity.getId()
                );

        String response = mockMvc.perform(

                post("/api/applications")
                        .header("Authorization", bearerToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(createRequest))

        ).andReturn()
         .getResponse()
         .getContentAsString();

        UUID applicationId =
                objectMapper.readTree(response)
                        .get("id")
                        .traverse()
                        .readValueAs(UUID.class);

        String invalidJson = """
        {
            "status":"INVALID_STATUS"
        }
        """;

        mockMvc.perform(

                patch("/api/applications/" + applicationId + "/status")
                        .header("Authorization", bearerToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson)

        )
        .andExpect(status().isBadRequest());

    }

}