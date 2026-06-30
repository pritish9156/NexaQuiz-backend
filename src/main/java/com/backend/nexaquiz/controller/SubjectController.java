package com.backend.nexaquiz.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backend.nexaquiz.dto.request.SubjectRequest;
import com.backend.nexaquiz.dto.response.SubjectResponse;
import com.backend.nexaquiz.service.SubjectService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/subjects")
@RequiredArgsConstructor
@CrossOrigin("*")
public class SubjectController {

    private final SubjectService subjectService;

    @PostMapping
    public ResponseEntity<SubjectResponse>
    createSubject(
            @Valid @RequestBody
            SubjectRequest request) {

        return ResponseEntity.status(
                        HttpStatus.CREATED)
                .body(subjectService
                        .createSubject(request));
    }

    @GetMapping
    public ResponseEntity<List<SubjectResponse>>
    getAllSubjects() {

        return ResponseEntity.ok(
                subjectService.getAllSubjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectResponse>
    getSubjectById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                subjectService.getSubjectById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubjectResponse>
    updateSubject(
            @PathVariable Long id,
            @RequestBody SubjectRequest request) {

        return ResponseEntity.ok(
                subjectService.updateSubject(
                        id,
                        request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>
    deleteSubject(
            @PathVariable Long id) {

        subjectService.deleteSubject(id);

        return ResponseEntity.noContent()
                .build();
    }
}