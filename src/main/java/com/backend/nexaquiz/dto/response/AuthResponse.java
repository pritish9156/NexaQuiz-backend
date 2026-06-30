package com.backend.nexaquiz.dto.response;

import com.backend.nexaquiz.entity.enums.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {

    private String token;

    private Long userId;

    private String fullName;

    private String email;

    private Role role;
}