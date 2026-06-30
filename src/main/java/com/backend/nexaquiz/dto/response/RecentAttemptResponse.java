package com.backend.nexaquiz.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecentAttemptResponse {

    private String studentName;

    private String quizTitle;

    private Integer score;

    private Double percentage;

}