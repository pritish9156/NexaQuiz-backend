package com.backend.nexaquiz.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SubjectRequest {

    @NotBlank
    private String name;

    private String description;
}