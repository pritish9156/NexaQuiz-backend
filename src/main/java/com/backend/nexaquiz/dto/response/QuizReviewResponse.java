package com.backend.nexaquiz.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuizReviewResponse {

    private String questionText;

    private String optionA;

    private String optionB;

    private String optionC;

    private String optionD;

    private String selectedAnswer;

    private String correctAnswer;

    private Boolean correct;

    private String codeSnippet;
}