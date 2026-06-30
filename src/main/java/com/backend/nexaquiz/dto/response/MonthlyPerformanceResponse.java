package com.backend.nexaquiz.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MonthlyPerformanceResponse {

    private String month;

    private Double score;

}