package com.backend.nexaquiz.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DashboardResponse {

    private Long totalStudents;

    private Long totalSubjects;

    private Long totalTopics;

    private Long totalQuestions;

    private Long totalQuizzes;

    private Double averageScore;

}