package com.backend.nexaquiz.controller;

import java.util.List;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.backend.nexaquiz.dto.request.QuestionRequest;
import com.backend.nexaquiz.dto.response.QuestionResponse;
import com.backend.nexaquiz.service.QuestionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
@CrossOrigin("*")
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping
    public ResponseEntity<QuestionResponse>
    createQuestion(
            @Valid @RequestBody QuestionRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(questionService.createQuestion(request));
    }

    @GetMapping
    public ResponseEntity<List<QuestionResponse>>
    getAllQuestions() {

        return ResponseEntity.ok(
                questionService.getAllQuestions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionResponse>
    getQuestionById(@PathVariable Long id) {

        return ResponseEntity.ok(
                questionService.getQuestionById(id));
    }

    @GetMapping("/subject/{subjectId}")
    public ResponseEntity<List<QuestionResponse>>
    getQuestionsBySubject(
            @PathVariable Long subjectId) {

        return ResponseEntity.ok(
                questionService.getQuestionsBySubject(subjectId));
    }

    @GetMapping("/topic/{topicId}")
    public ResponseEntity<List<QuestionResponse>>
    getQuestionsByTopic(
            @PathVariable Long topicId) {

        return ResponseEntity.ok(
                questionService.getQuestionsByTopic(topicId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuestionResponse>
    updateQuestion(
            @PathVariable Long id,
            @RequestBody QuestionRequest request) {

        return ResponseEntity.ok(
                questionService.updateQuestion(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>
    deleteQuestion(@PathVariable Long id) {

        questionService.deleteQuestion(id);

        return ResponseEntity.noContent().build();
    }
}