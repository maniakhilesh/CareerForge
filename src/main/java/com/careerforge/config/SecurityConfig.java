package com.careerforge.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.
        UsernamePasswordAuthenticationFilter;

import com.careerforge.auth.security.
        JwtAuthenticationFilter;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {

        http
        
                .csrf(csrf -> csrf.disable())
                .exceptionHandling(ex -> ex
    .authenticationEntryPoint(
        (request, response, authException) ->
            response.sendError(
                HttpServletResponse.SC_UNAUTHORIZED,
                "Unauthorized"
            )
    )
)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )
               .authorizeHttpRequests(auth -> auth

    .requestMatchers(
    "/api/auth/**",
    "/swagger-ui/**",
    "/v3/api-docs/**",
    "/actuator/**"
)
.permitAll()

    .anyRequest()
    .authenticated()
)
                .addFilterBefore(
                        jwtFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}