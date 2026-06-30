package com.backend.nexaquiz.controller;

import java.util.List;

import com.backend.nexaquiz.dto.request.QuizSubmissionRequest;
import com.backend.nexaquiz.dto.response.*;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.backend.nexaquiz.dto.request.QuizRequest;
import com.backend.nexaquiz.service.QuizService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/quizzes")
@RequiredArgsConstructor
@CrossOrigin("*")
public class QuizController {

    private final QuizService quizService;

    @PostMapping
    public ResponseEntity<QuizResponse> createQuiz(
            @Valid @RequestBody QuizRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(quizService.createQuiz(request));
    }

    @GetMapping
    public ResponseEntity<List<QuizResponse>> getAllQuizzes() {

        return ResponseEntity.ok(
                quizService.getAllQuizzes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizResponse> getQuizById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                quizService.getQuizById(id));
    }

    @PutMapping("/{id}/publish")
    public ResponseEntity<QuizResponse> publishQuiz(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                quizService.publishQuiz(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuiz(
            @PathVariable Long id) {

        quizService.deleteQuiz(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuizResponse>
    updateQuiz(
            @PathVariable Long id,
            @RequestBody QuizRequest request) {

        return ResponseEntity.ok(
                quizService.updateQuiz(
                        id,
                        request
                ));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<QuizResponse>>
    getStudentQuizzes(

            @PathVariable Long studentId){

        return ResponseEntity.ok(

                quizService
                        .getQuizzesForStudent(
                                studentId
                        )

        );

    }

    @GetMapping("/{quizId}/questions")
    public ResponseEntity<List<QuestionResponse>>
    getQuizQuestions(

            @PathVariable Long quizId){

        return ResponseEntity.ok(

                quizService
                        .getQuizQuestions(
                                quizId
                        )

        );

    }

    @PostMapping("/{quizId}/submit")
    public ResponseEntity<QuizResultResponse>
    submitQuiz(

            @PathVariable Long quizId,

            @RequestBody
            QuizSubmissionRequest request){

        return ResponseEntity.ok(

                quizService.submitQuiz(
                        quizId,
                        request
                )

        );
    }

    @GetMapping("/student/{studentId}/history")
    public ResponseEntity<
            List<QuizAttemptResponse>>
    getHistory(

            @PathVariable Long studentId){

        return ResponseEntity.ok(

                quizService
                        .getStudentAttempts(
                                studentId
                        )

        );
    }

    @GetMapping(
            "/student/{studentId}/performance"
    )
    public ResponseEntity<
            StudentPerformanceResponse>
    getPerformance(

            @PathVariable
            Long studentId){

        return ResponseEntity.ok(

                quizService
                        .getStudentPerformance(
                                studentId
                        )

        );
    }

    @GetMapping(
            "/{quizId}/analytics"
    )
    public ResponseEntity<
            QuizAnalyticsResponse>
    getQuizAnalytics(

            @PathVariable
            Long quizId){

        return ResponseEntity.ok(

                quizService
                        .getQuizAnalytics(
                                quizId
                        )

        );

    }

    @GetMapping(
            "/{quizId}/attempts"
    )
    public ResponseEntity<
            List<QuizAttemptAnalyticsResponse>>
    getQuizAttempts(

            @PathVariable
            Long quizId){

        return ResponseEntity.ok(

                quizService
                        .getQuizAttemptHistory(
                                quizId
                        )

        );

    }

    @GetMapping(
            "/student/{studentId}/profile"
    )
    public ResponseEntity<
            StudentProfileResponse>
    getStudentProfile(

            @PathVariable
            Long studentId){

        return ResponseEntity.ok(

                quizService
                        .getStudentProfile(
                                studentId
                        )

        );

    }

    @GetMapping(
            "/attempts/{attemptId}/review"
    )
    public ResponseEntity<
            List<QuizReviewResponse>>
    getAttemptReview(

            @PathVariable
            Long attemptId){

        return ResponseEntity.ok(

                quizService
                        .getAttemptReview(
                                attemptId
                        )

        );

    }

    @GetMapping(
            "/student/{studentId}/dashboard"
    )
    public ResponseEntity<
            StudentDashboardResponse>
    getStudentDashboard(

            @PathVariable
            Long studentId){

        return ResponseEntity.ok(

                quizService
                        .getStudentDashboard(
                                studentId
                        )

        );
    }

    @GetMapping("/results")

    @PreAuthorize("hasRole('ADMIN')")

    public List<AdminResultResponse>
    getAllResults(){

        return quizService.getAllResults();

    }
}