package com.backend.nexaquiz.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubmitQuizResponse {

    private Integer totalQuestions;

    private Integer correctAnswers;

    private Integer wrongAnswers;

    private Integer score;

    private Double percentage;
}