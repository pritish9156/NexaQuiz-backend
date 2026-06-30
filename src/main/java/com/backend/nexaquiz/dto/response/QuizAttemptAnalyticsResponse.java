package com.backend.nexaquiz.dto.response;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuizAttemptAnalyticsResponse {

    private String studentName;

    private Integer score;

    private Double percentage;

    private LocalDateTime attemptedAt;

}