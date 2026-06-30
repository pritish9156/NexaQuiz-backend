package com.backend.nexaquiz.service;

import java.util.List;

import com.backend.nexaquiz.dto.response.PerformanceSummaryResponse;
import com.backend.nexaquiz.dto.response.StudentResultResponse;

public interface ResultService {

    List<StudentResultResponse> getStudentResults(
            Long studentId);

    PerformanceSummaryResponse getPerformanceSummary(
            Long studentId);
}