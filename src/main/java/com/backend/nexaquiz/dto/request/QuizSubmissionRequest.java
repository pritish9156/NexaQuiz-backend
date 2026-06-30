package com.backend.nexaquiz.dto.request;

import java.util.Map;

import lombok.Data;

@Data
public class QuizSubmissionRequest {

    private Long studentId;

    private Map<Long,String> answers;

}