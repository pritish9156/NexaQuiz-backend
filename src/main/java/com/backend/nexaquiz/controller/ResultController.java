package com.backend.nexaquiz.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backend.nexaquiz.dto.response.PerformanceSummaryResponse;
import com.backend.nexaquiz.dto.response.StudentResultResponse;
import com.backend.nexaquiz.service.ResultService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ResultController {

    private final ResultService resultService;

    @GetMapping("/{studentId}/results")
    public ResponseEntity<List<StudentResultResponse>>
    getResults(
            @PathVariable Long studentId) {

        return ResponseEntity.ok(
                resultService.getStudentResults(
                        studentId));
    }

    @GetMapping("/{studentId}/performance")
    public ResponseEntity<PerformanceSummaryResponse>
    getPerformance(
            @PathVariable Long studentId) {

        return ResponseEntity.ok(
                resultService.getPerformanceSummary(
                        studentId));
    }
}