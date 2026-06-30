package com.backend.nexaquiz.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backend.nexaquiz.dto.request.AssignStudentsRequest;
import com.backend.nexaquiz.dto.response.BatchStudentResponse;
import com.backend.nexaquiz.service.BatchStudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/batch-students")
@RequiredArgsConstructor
@CrossOrigin("*")
public class BatchStudentController {

    private final BatchStudentService
            batchStudentService;

    @PostMapping("/assign")
    public ResponseEntity<Void>
    assignStudentsToBatch(

            @RequestBody
            AssignStudentsRequest request) {

        batchStudentService
                .assignStudentsToBatch(
                        request);

        return ResponseEntity.ok()
                .build();
    }

    @GetMapping("/{batchId}")
    public ResponseEntity<
            List<BatchStudentResponse>>
    getStudentsByBatch(

            @PathVariable Long batchId) {

        return ResponseEntity.ok(

                batchStudentService
                        .getStudentsByBatch(
                                batchId)

        );
    }
}