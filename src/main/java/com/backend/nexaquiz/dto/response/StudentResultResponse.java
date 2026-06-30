package com.backend.nexaquiz.dto.response;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentResultResponse {

    private Long attemptId;

    private String quizTitle;

    private String subjectName;

    private String topicName;

    private LocalDateTime attemptedOn;

    private Integer totalMarks;

    private Integer obtainedMarks;

    private Double percentage;

    private String status;
}