package com.backend.nexaquiz.dto.request;

import com.backend.nexaquiz.entity.enums.DifficultyLevel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class QuestionRequest {

    @NotBlank
    private String questionText;

    @NotBlank
    private String optionA;

    @NotBlank
    private String optionB;

    @NotBlank
    private String optionC;

    @NotBlank
    private String optionD;

    @NotBlank
    private String correctAnswer;

    private String codeSnippet;

    @NotNull
    private DifficultyLevel difficultyLevel;

    @NotNull
    private Long subjectId;

    @NotNull
    private Long topicId;
}