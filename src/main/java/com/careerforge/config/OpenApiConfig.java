package com.careerforge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {

    private static final String SECURITY_SCHEME_NAME = "Bearer Authentication";

    @Bean
    public OpenAPI careerForgeApi() {

        return new OpenAPI()

                // Development Server
                .addServersItem(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Development Server")
                )

                // API Information
                .info(
                        new Info()
                                .title("CareerForge Backend API")
                                .version("v1.0.0")
                                .description("""
                                        CareerForge Backend is a production-ready REST API built with Spring Boot
                                        for managing internships, job opportunities, and application tracking.

                                        Features:
                                        • JWT Authentication
                                        • User Management
                                        • Opportunity Management
                                        • Application Tracking
                                        • PostgreSQL Database
                                        • Flyway Database Migrations
                                        • Docker Support
                                        • Spring Boot Actuator
                                        • OpenAPI / Swagger Documentation
                                        """)
                                .contact(
                                        new Contact()
                                                .name("Mani Akhilesh Kumar")
                                                .email("maniakhileshedu@gmail.com")
                                                .url("https://github.com/maniakhilesh")
                                )
                                .license(
                                        new License()
                                                .name("MIT License")
                                                .url("https://opensource.org/licenses/MIT")
                                )
                )

                // JWT Security
                .addSecurityItem(
                        new SecurityRequirement()
                                .addList(SECURITY_SCHEME_NAME)
                )

                .components(
                        new Components()
                                .addSecuritySchemes(
                                        SECURITY_SCHEME_NAME,
                                        new SecurityScheme()
                                                .name(SECURITY_SCHEME_NAME)
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                )
                );
    }
}