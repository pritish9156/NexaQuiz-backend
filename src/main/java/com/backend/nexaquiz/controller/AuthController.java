package com.backend.nexaquiz.controller;

import com.backend.nexaquiz.dto.request.ForgotPasswordRequest;
import com.backend.nexaquiz.dto.request.ResetPasswordRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.backend.nexaquiz.dto.request.LoginRequest;
import com.backend.nexaquiz.dto.request.RegisterRequest;
import com.backend.nexaquiz.dto.response.AuthResponse;
import com.backend.nexaquiz.service.AuthService;
import com.backend.nexaquiz.dto.request.ForgotPasswordRequest;
import com.backend.nexaquiz.dto.request.ResetPasswordRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Validated
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @Valid @RequestBody RegisterRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @Valid @RequestBody LoginRequest request) {

        return ResponseEntity.ok(
                authService.login(request));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String>
    forgotPassword(
            @RequestBody
            ForgotPasswordRequest request) {

        authService.forgotPassword(request);

        return ResponseEntity.ok(
                "Password reset link sent");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String>
    resetPassword(
            @RequestBody
            ResetPasswordRequest request) {

        authService.resetPassword(request);

        return ResponseEntity.ok(
                "Password reset successful");
    }


}