package com.backend.nexaquiz.serviceImpl;

import java.util.DoubleSummaryStatistics;
import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.nexaquiz.dto.response.PerformanceSummaryResponse;
import com.backend.nexaquiz.dto.response.StudentResultResponse;
import com.backend.nexaquiz.entity.QuizAttempt;
import com.backend.nexaquiz.repository.QuizAttemptRepository;
import com.backend.nexaquiz.service.ResultService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {

    private final QuizAttemptRepository quizAttemptRepository;

    @Override
    public List<StudentResultResponse> getStudentResults(
            Long studentId) {

        return quizAttemptRepository
                .findByStudentIdOrderByCreatedAtDesc(studentId)
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public PerformanceSummaryResponse getPerformanceSummary(
            Long studentId) {

        List<QuizAttempt> attempts =
                quizAttemptRepository
                        .findByStudentId(studentId);

        if (attempts.isEmpty()) {

            return PerformanceSummaryResponse.builder()
                    .totalQuizzes(0)
                    .averageScore(0.0)
                    .highestScore(0.0)
                    .lowestScore(0.0)
                    .build();
        }

        DoubleSummaryStatistics stats =
                attempts.stream()
                        .mapToDouble(
                                QuizAttempt::getPercentage)
                        .summaryStatistics();

        return PerformanceSummaryResponse.builder()
                .totalQuizzes(attempts.size())
                .averageScore(
                        Math.round(stats.getAverage() * 100.0) / 100.0)
                .highestScore(stats.getMax())
                .lowestScore(stats.getMin())
                .build();
    }

    private StudentResultResponse map(
            QuizAttempt attempt) {

        return StudentResultResponse.builder()
                .attemptId(attempt.getId())
                .quizTitle(
                        attempt.getQuiz().getTitle())
                .subjectName(
                        attempt.getQuiz()
                                .getSubject()
                                .getName())
                .topicName(
                        attempt.getQuiz()
                                .getTopic()
                                .getName())
                .attemptedOn(
                        attempt.getCreatedAt())
                .totalMarks(
                        attempt.getTotalQuestions())
                .obtainedMarks(
                        attempt.getScore())
                .percentage(
                        attempt.getPercentage())
                .status(
                        attempt.getStatus().name())
                .build();
    }
}