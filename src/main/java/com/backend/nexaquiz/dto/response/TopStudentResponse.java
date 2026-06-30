package com.backend.nexaquiz.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TopStudentResponse {

    private Long studentId;

    private String studentName;

    private Double averagePercentage;

}