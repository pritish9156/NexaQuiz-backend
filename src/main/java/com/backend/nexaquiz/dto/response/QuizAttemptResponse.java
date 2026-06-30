package com.backend.nexaquiz.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuizAttemptResponse {

    private Long attemptId;

    private String quizTitle;

    private String subjectName;

    private String topicName;

    private String quizDate;

    private Integer totalMarks;

    private Integer correctAnswers;

    private Integer wrongAnswers;

    private Integer score;

    private Integer marksObtained;

    private Double percentage;

    private String status;

}