package com.backend.nexaquiz.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentDashboardResponse {

    private Integer availableQuizzes;

    private Integer completedQuizzes;

    private Double averageScore;

    private String studentName;

}