package com.backend.nexaquiz.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentProfileResponse {

    private Long studentId;

    private String fullName;

    private String email;

    private String batchName;

    private Integer totalQuizzes;

    private Double averageScore;

    private Double bestScore;

}