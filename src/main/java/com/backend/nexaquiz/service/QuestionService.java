package com.backend.nexaquiz.service;

import java.util.List;

import com.backend.nexaquiz.dto.request.QuestionRequest;
import com.backend.nexaquiz.dto.response.QuestionResponse;

public interface QuestionService {

    QuestionResponse createQuestion(
            QuestionRequest request);

    QuestionResponse updateQuestion(
            Long id,
            QuestionRequest request);

    void deleteQuestion(Long id);

    QuestionResponse getQuestionById(Long id);

    List<QuestionResponse> getAllQuestions();

    List<QuestionResponse> getQuestionsBySubject(
            Long subjectId);

    List<QuestionResponse> getQuestionsByTopic(
            Long topicId);
}