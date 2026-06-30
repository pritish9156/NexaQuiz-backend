package com.backend.nexaquiz.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TopicRequest {

    @NotBlank
    private String name;

    private String description;

    @NotNull
    private Long subjectId;
}