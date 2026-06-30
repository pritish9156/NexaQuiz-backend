package com.backend.nexaquiz.controller;

import com.backend.nexaquiz.dto.request.BatchRequest;
import com.backend.nexaquiz.dto.response.BatchResponse;
import com.backend.nexaquiz.service.BatchService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/batches")
@RequiredArgsConstructor
@CrossOrigin("*")
public class BatchController {

    private final BatchService batchService;

    @PostMapping
    public ResponseEntity<BatchResponse>
    createBatch(
            @Valid
            @RequestBody
            BatchRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        batchService.createBatch(
                                request));
    }

    @GetMapping
    public ResponseEntity<List<BatchResponse>>
    getAllBatches() {

        return ResponseEntity.ok(
                batchService.getAllBatches());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BatchResponse>
    getBatchById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                batchService.getBatchById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BatchResponse>
    updateBatch(
            @PathVariable Long id,
            @RequestBody BatchRequest request) {

        return ResponseEntity.ok(
                batchService.updateBatch(
                        id,
                        request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>
    deleteBatch(
            @PathVariable Long id) {

        batchService.deleteBatch(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}