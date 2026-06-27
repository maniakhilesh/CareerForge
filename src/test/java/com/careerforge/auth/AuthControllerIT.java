package com.careerforge.auth;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.careerforge.IntegrationTestConfig;
import com.careerforge.auth.dto.LoginRequest;
import com.careerforge.auth.dto.RegisterRequest;
import com.careerforge.util.JsonUtil;
import com.careerforge.util.TestDataFactory;

class AuthControllerIT extends IntegrationTestConfig {

    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldRegisterUserSuccessfully() throws Exception {

        RegisterRequest request =
                TestDataFactory.registerRequest();

        mockMvc.perform(

                post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(request))

        )
        .andExpect(status().isCreated());

    }

    @Test
    void shouldLoginSuccessfully() throws Exception {

        RegisterRequest register =
                TestDataFactory.registerRequest();

        mockMvc.perform(

                post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(register))

        );

        LoginRequest login =
                TestDataFactory.loginRequest();

        mockMvc.perform(

                post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(login))

        )
        .andExpect(status().isOk());

    }
@Test
void shouldRejectDuplicateEmailRegistration() throws Exception {

    RegisterRequest request =
            TestDataFactory.registerRequest();

    // First registration
    mockMvc.perform(
            post("/api/auth/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(JsonUtil.toJson(request))
    );

    // Duplicate registration
    mockMvc.perform(
            post("/api/auth/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(JsonUtil.toJson(request))
    )
    .andExpect(status().isConflict());

}
@Test
void shouldRejectInvalidEmail() throws Exception {

    RegisterRequest request =
            new RegisterRequest(
                    "Mani",
                    "invalid-email",
                    "Password@123"
            );

    mockMvc.perform(
            post("/api/auth/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(JsonUtil.toJson(request))
    )
    .andExpect(status().isBadRequest());

}
@Test
void shouldRejectBlankPassword() throws Exception {

    RegisterRequest request =
            new RegisterRequest(
                    "Mani",
                    "mani@test.com",
                    ""
            );

    mockMvc.perform(
            post("/api/auth/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(JsonUtil.toJson(request))
    )
    .andExpect(status().isBadRequest());

}
@Test
void shouldRejectWrongPassword() throws Exception {

    RegisterRequest register =
            TestDataFactory.registerRequest();

    mockMvc.perform(
            post("/api/auth/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(JsonUtil.toJson(register))
    );

    LoginRequest login =
            TestDataFactory.invalidLoginRequest();

    mockMvc.perform(
            post("/api/auth/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(JsonUtil.toJson(login))
    )
    .andExpect(status().isUnauthorized());

}
@Test
void shouldRejectUnknownUser() throws Exception {

    LoginRequest login =
            new LoginRequest(
                    "unknown@test.com",
                    "Password@123"
            );

    mockMvc.perform(
            post("/api/auth/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(JsonUtil.toJson(login))
    )
    .andExpect(status().isUnauthorized());

}
}