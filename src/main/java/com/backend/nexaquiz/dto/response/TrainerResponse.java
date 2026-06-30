package com.backend.nexaquiz.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TrainerResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private Boolean enabled;

}