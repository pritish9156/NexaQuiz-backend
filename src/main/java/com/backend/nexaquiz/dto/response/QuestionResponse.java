package com.backend.nexaquiz.dto.response;

import com.backend.nexaquiz.entity.enums.DifficultyLevel;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuestionResponse {

    private Long id;

    private Long subjectId;

    private Long topicId;

    private String questionText;

    private String optionA;

    private String optionB;

    private String optionC;

    private String optionD;

    private String correctAnswer;

    private String codeSnippet;

    private DifficultyLevel difficultyLevel;

    private String subjectName;

    private String topicName;

    private Boolean active;
}