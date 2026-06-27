package com.careerforge.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.careerforge.auth.dto.AuthResponse;
import com.careerforge.auth.dto.LoginRequest;
import com.careerforge.auth.dto.RegisterRequest;
import com.careerforge.auth.security.JwtService;
import com.careerforge.common.exception.EmailAlreadyExistsException;
import com.careerforge.common.exception.InvalidCredentialsException;
import com.careerforge.user.entity.User;
import com.careerforge.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    public void register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.email())) {
            throw new EmailAlreadyExistsException(request.email());
        }

        User user = User.builder()
                .name(request.name())
                .email(request.email())
                .passwordHash(
                        passwordEncoder.encode(request.password())
                )
                .build();

        userRepository.save(user);
    }

    public AuthResponse login(LoginRequest request) {

    User user = userRepository
            .findByEmail(request.email())
            .orElseThrow(InvalidCredentialsException::new);

    boolean passwordMatches =
            passwordEncoder.matches(
                    request.password(),
                    user.getPasswordHash()
            );

    if (!passwordMatches) {
        throw new InvalidCredentialsException();
    }

    String token =
            jwtService.generateToken(
                    user.getEmail()
            );

    return new AuthResponse(token);
}
}