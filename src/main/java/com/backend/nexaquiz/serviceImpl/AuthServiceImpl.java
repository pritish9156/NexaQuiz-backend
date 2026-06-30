package com.backend.nexaquiz.serviceImpl;

import com.backend.nexaquiz.dto.request.ForgotPasswordRequest;
import com.backend.nexaquiz.dto.request.ResetPasswordRequest;
import com.backend.nexaquiz.entity.PasswordResetToken;
import com.backend.nexaquiz.exception.ResourceNotFoundException;
import com.backend.nexaquiz.repository.PasswordResetTokenRepository;
import com.backend.nexaquiz.service.EmailService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.nexaquiz.dto.request.LoginRequest;
import com.backend.nexaquiz.dto.request.RegisterRequest;
import com.backend.nexaquiz.dto.response.AuthResponse;
import com.backend.nexaquiz.entity.User;
import com.backend.nexaquiz.exception.DuplicateResourceException;
import com.backend.nexaquiz.repository.UserRepository;
import com.backend.nexaquiz.security.JwtUtil;
import com.backend.nexaquiz.service.AuthService;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final PasswordResetTokenRepository tokenRepository;


    private final JwtUtil jwtUtil;

    private final EmailService emailService;

    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException(
                    "Email already exists");
        }

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(
                        passwordEncoder.encode(
                                request.getPassword()))
                .role(request.getRole())
                .enabled(true)
                .build();

        userRepository.save(user);

        String token =
                jwtUtil.generateToken(user.getEmail());

        return AuthResponse.builder()
                .token(token)
                .userId(user.getId())
                .fullName(
                        user.getFirstName() + " "
                                + user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));

        User user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow();

        String token =
                jwtUtil.generateToken(user.getEmail());

        return AuthResponse.builder()
                .token(token)
                .userId(user.getId())
                .fullName(
                        user.getFirstName() + " "
                                + user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    @Override
    public void forgotPassword(
            ForgotPasswordRequest request) {

        User user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"));

        String token =
                UUID.randomUUID().toString();

        PasswordResetToken resetToken =
                PasswordResetToken.builder()
                        .token(token)
                        .user(user)
                        .used(false)
                        .expiryTime(
                                LocalDateTime.now()
                                        .plusMinutes(30))
                        .build();

        tokenRepository.save(resetToken);

        String link =
                "http://localhost:5173/reset-password?token="
                        + token;

        emailService.sendEmail(
                user.getEmail(),
                "Reset Password",
                "Click below link:\n\n" + link);
    }

    @Override
    public void resetPassword(
            ResetPasswordRequest request) {

        PasswordResetToken token =
                tokenRepository
                        .findByToken(
                                request.getToken())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Invalid token"));

        if(token.getUsed()) {
            throw new RuntimeException(
                    "Token already used");
        }

        if(token.getExpiryTime()
                .isBefore(LocalDateTime.now())) {

            throw new RuntimeException(
                    "Token expired");
        }

        User user = token.getUser();

        user.setPassword(
                passwordEncoder.encode(
                        request.getNewPassword()));

        userRepository.save(user);

        token.setUsed(true);

        tokenRepository.save(token);
    }
}