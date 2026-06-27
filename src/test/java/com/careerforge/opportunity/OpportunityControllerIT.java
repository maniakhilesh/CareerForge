package com.careerforge.opportunity;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.careerforge.IntegrationTestConfig;
import com.careerforge.opportunity.dto.CreateOpportunityRequest;
import com.careerforge.opportunity.entity.Opportunity;
import com.careerforge.util.JsonUtil;
import com.careerforge.util.TestDataFactory;

class OpportunityControllerIT extends IntegrationTestConfig {

    private String bearerToken;

    @BeforeEach
    void setup() {

        opportunityRepository.deleteAll();

        bearerToken = authHelper.bearerToken();
    }

    @Test
    void shouldCreateOpportunity() throws Exception {

        CreateOpportunityRequest request =
                TestDataFactory.opportunityRequest();

        mockMvc.perform(

                post("/api/opportunities")
                        .header("Authorization", bearerToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(request))

        )
        .andExpect(status().isCreated());

    }

    @Test
    void shouldRejectCreateWithoutJwt() throws Exception {

        CreateOpportunityRequest request =
                TestDataFactory.opportunityRequest();

        mockMvc.perform(

                post("/api/opportunities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(request))

        )
        .andExpect(status().isUnauthorized());

    }

    @Test
    void shouldRejectBlankTitle() throws Exception {

        CreateOpportunityRequest request =
                new CreateOpportunityRequest(
                        "",
                        "Google",
                        "Bengaluru",
                        "50000",
                        "Backend Internship",
                        "https://careers.google.com",
                        "Internship"
                );

        mockMvc.perform(

                post("/api/opportunities")
                        .header("Authorization", bearerToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(request))

        )
        .andExpect(status().isBadRequest());

    }

    @Test
    void shouldListAllOpportunities() throws Exception {

        opportunityRepository.save(

                Opportunity.builder()
                        .title("Software Engineer Intern")
                        .company("Google")
                        .location("Bengaluru")
                        .salary("50000")
                        .description("Spring Boot Internship")
                        .employmentType("Internship")
                        .build()

        );

        mockMvc.perform(

                get("/api/opportunities")
                        .header("Authorization", bearerToken)

        )
        .andExpect(status().isOk());

    }
        @Test
    void shouldGetOpportunityById() throws Exception {

        Opportunity saved = opportunityRepository.save(

                Opportunity.builder()
                        .title("Backend Engineer")
                        .company("Microsoft")
                        .location("Hyderabad")
                        .salary("70000")
                        .description("Java Spring Boot")
                        .employmentType("Full Time")
                        .build()

        );

        mockMvc.perform(

                get("/api/opportunities/" + saved.getId())
                        .header("Authorization", bearerToken)

        )
        .andExpect(status().isOk());

    }

    @Test
    void shouldReturn404WhenOpportunityDoesNotExist() throws Exception {

        mockMvc.perform(

                get("/api/opportunities/" + UUID.randomUUID())
                        .header("Authorization", bearerToken)

        )
        .andExpect(status().isNotFound());

    }

    @Test
    void shouldDeleteOpportunity() throws Exception {

        Opportunity saved = opportunityRepository.save(

                Opportunity.builder()
                        .title("SDE Intern")
                        .company("Amazon")
                        .location("Bangalore")
                        .salary("80000")
                        .description("Backend Development")
                        .employmentType("Internship")
                        .build()

        );

        mockMvc.perform(

                delete("/api/opportunities/" + saved.getId())
                        .header("Authorization", bearerToken)

        )
        .andExpect(status().isNoContent());

    }

    @Test
    void shouldRejectDeleteWithoutJwt() throws Exception {

        Opportunity saved = opportunityRepository.save(

                Opportunity.builder()
                        .title("SDE Intern")
                        .company("Amazon")
                        .location("Bangalore")
                        .salary("80000")
                        .description("Backend Development")
                        .employmentType("Internship")
                        .build()

        );

        mockMvc.perform(

                delete("/api/opportunities/" + saved.getId())

        )
        .andExpect(status().isUnauthorized());

    }

    @Test
    void shouldReturn404WhenDeletingUnknownOpportunity() throws Exception {

        mockMvc.perform(

                delete("/api/opportunities/" + UUID.randomUUID())
                        .header("Authorization", bearerToken)

        )
        .andExpect(status().isNotFound());

    }

}
