package com.backend.nexaquiz.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PerformanceSummaryResponse {

    private Integer totalQuizzes;

    private Double averageScore;

    private Double highestScore;

    private Double lowestScore;
}