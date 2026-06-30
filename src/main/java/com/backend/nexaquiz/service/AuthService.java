package com.backend.nexaquiz.service;

import com.backend.nexaquiz.dto.request.ForgotPasswordRequest;
import com.backend.nexaquiz.dto.request.LoginRequest;
import com.backend.nexaquiz.dto.request.RegisterRequest;
import com.backend.nexaquiz.dto.request.ResetPasswordRequest;
import com.backend.nexaquiz.dto.response.AuthResponse;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);

    void forgotPassword(
            ForgotPasswordRequest request);

    void resetPassword(
            ResetPasswordRequest request);

}