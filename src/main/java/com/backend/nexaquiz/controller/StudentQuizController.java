package com.backend.nexaquiz.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backend.nexaquiz.dto.request.SubmitAnswerRequest;
import com.backend.nexaquiz.dto.response.*;
import com.backend.nexaquiz.service.QuizAttemptService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/student/quizzes")
@RequiredArgsConstructor
@CrossOrigin("*")
public class StudentQuizController {

    private final QuizAttemptService quizAttemptService;

    @PostMapping("/{quizId}/start")
    public ResponseEntity<StartQuizResponse> startQuiz(
            @PathVariable Long quizId,
            @RequestParam Long studentId) {

        return ResponseEntity.ok(
                quizAttemptService.startQuiz(
                        quizId,
                        studentId));
    }

    @GetMapping("/{quizId}/questions")
    public ResponseEntity<List<QuizQuestionResponse>>
    getQuestions(
            @PathVariable Long quizId) {

        return ResponseEntity.ok(
                quizAttemptService.getQuestions(
                        quizId));
    }

    @PostMapping("/save-answer")
    public ResponseEntity<String> saveAnswer(
            @RequestBody SubmitAnswerRequest request) {

        quizAttemptService.saveAnswer(request);

        return ResponseEntity.ok("Answer Saved");
    }

    @PostMapping("/{attemptId}/submit")
    public ResponseEntity<SubmitQuizResponse>
    submitQuiz(
            @PathVariable Long attemptId) {

        return ResponseEntity.ok(
                quizAttemptService.submitQuiz(
                        attemptId));
    }
}