package com.backend.nexaquiz.dto.response;

import com.backend.nexaquiz.entity.enums.Role;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private Role role;

    private Boolean enabled;
}