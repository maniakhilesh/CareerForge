package com.careerforge.util;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.careerforge.auth.security.JwtService;
import com.careerforge.user.entity.User;
import com.careerforge.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TestAuthenticationHelper {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User createTestUser() {

        return userRepository.findByEmail("mani@test.com")
                .orElseGet(() ->

                        userRepository.save(

                                User.builder()
                                        .name("Mani Akhilesh Kumar")
                                        .email("mani@test.com")
                                        .passwordHash(
                                                passwordEncoder.encode("Password@123")
                                        )
                                        .build()

                        )

                );

    }

    public String bearerToken() {

        User user = createTestUser();

        return "Bearer " + jwtService.generateToken(user.getEmail());

    }

}