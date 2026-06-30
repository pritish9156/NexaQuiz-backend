package com.backend.nexaquiz.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StudentPerformanceResponse {

    private Integer totalQuizzes;

    private Double averageScore;

    private Double bestScore;

    private List<QuizAttemptResponse> attempts;

}