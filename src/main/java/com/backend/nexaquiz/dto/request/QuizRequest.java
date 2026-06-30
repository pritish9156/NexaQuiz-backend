package com.backend.nexaquiz.dto.request;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class QuizRequest {

    @NotBlank
    private String title;

    private String description;

    @NotNull
    private Integer durationMinutes;

    @NotNull
    private Long batchId;

    @NotNull
    private Long subjectId;

    @NotNull
    private Long topicId;

    @NotNull
    private LocalDateTime scheduledAt;

    @NotNull
    private Integer totalQuestions;

}