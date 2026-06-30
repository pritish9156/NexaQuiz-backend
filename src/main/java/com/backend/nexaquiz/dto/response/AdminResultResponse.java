package com.backend.nexaquiz.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminResultResponse {

    private Long attemptId;

    private String studentName;

    private String batchName;

    private String quizTitle;

    private String subjectName;

    private String topicName;

    private Integer score;

    private Double percentage;

    private Integer correctAnswers;

    private Integer wrongAnswers;

    private String status;

    private String submittedDate;

}