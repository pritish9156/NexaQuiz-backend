package com.backend.nexaquiz.service;

import java.util.List;

import com.backend.nexaquiz.dto.request.SubmitAnswerRequest;
import com.backend.nexaquiz.dto.response.*;

public interface QuizAttemptService {

    StartQuizResponse startQuiz(
            Long quizId,
            Long studentId);

    void saveAnswer(
            SubmitAnswerRequest request);

    SubmitQuizResponse submitQuiz(
            Long attemptId);

    List<QuizQuestionResponse> getQuestions(
            Long quizId);
}