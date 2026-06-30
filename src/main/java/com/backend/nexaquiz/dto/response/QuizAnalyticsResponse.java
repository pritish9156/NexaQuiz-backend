package com.backend.nexaquiz.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuizAnalyticsResponse {

    private Long quizId;

    private String quizTitle;

    private Integer totalAttempts;

    private Double averageScore;

    private Double highestScore;

    private Double lowestScore;

}