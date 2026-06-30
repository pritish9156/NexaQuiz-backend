package com.backend.nexaquiz.dto.request;

import lombok.Data;

@Data
public class SubmitAnswerRequest {

    private Long attemptId;

    private Long questionId;

    private String selectedAnswer;
}