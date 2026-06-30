package com.backend.nexaquiz.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import com.backend.nexaquiz.entity.enums.QuizStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuizResponse {

    private Long id;

    private String title;

    private String description;

    private Integer durationMinutes;

    private Integer totalMarks;

    private Long batchId;

    private String batchName;

    private Long subjectId;

    private String subjectName;

    private Long topicId;

    private String topicName;

    private Integer totalQuestions;

    private LocalDateTime scheduledAt;

    private QuizStatus status;

    private Boolean available;

    private Boolean attempted;
}