package com.backend.nexaquiz.dto.response;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StartQuizResponse {

    private Long attemptId;

    private Long quizId;

    private String quizTitle;

    private Integer durationMinutes;

    private LocalDateTime startTime;

    private Integer totalQuestions;
}