package com.backend.nexaquiz.service;

import java.util.List;

import com.backend.nexaquiz.dto.request.QuizRequest;
import com.backend.nexaquiz.dto.request.QuizSubmissionRequest;
import com.backend.nexaquiz.dto.response.*;

public interface QuizService {

    QuizResponse createQuiz(QuizRequest request);

    List<QuizResponse> getAllQuizzes();

    QuizResponse getQuizById(Long id);

    QuizResponse publishQuiz(Long id);

    void deleteQuiz(Long id);

    QuizResponse updateQuiz(
            Long id,
            QuizRequest request
    );

    List<QuizResponse>
    getQuizzesForStudent(
            Long studentId);

    List<QuestionResponse>
    getQuizQuestions(
            Long quizId
    );

    QuizResultResponse submitQuiz(
            Long quizId,
            QuizSubmissionRequest request
    );

    List<QuizAttemptResponse>
    getStudentAttempts(
            Long studentId
    );

    StudentPerformanceResponse
    getStudentPerformance(
            Long studentId
    );

    QuizAnalyticsResponse
    getQuizAnalytics(
            Long quizId
    );

    List<QuizAttemptAnalyticsResponse>
    getQuizAttemptHistory(
            Long quizId
    );

    StudentProfileResponse
    getStudentProfile(
            Long studentId
    );

    List<QuizReviewResponse>
    getAttemptReview(
            Long attemptId
    );

    StudentDashboardResponse
    getStudentDashboard(
            Long studentId
    );

    List<AdminResultResponse> getAllResults();
}