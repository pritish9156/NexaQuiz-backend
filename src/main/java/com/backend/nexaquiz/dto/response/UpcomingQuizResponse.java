package com.backend.nexaquiz.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UpcomingQuizResponse {

    private String title;

    private String subjectName;

    private LocalDateTime scheduledAt;

}