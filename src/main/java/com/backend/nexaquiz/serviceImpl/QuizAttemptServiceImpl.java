package com.backend.nexaquiz.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.nexaquiz.dto.request.SubmitAnswerRequest;
import com.backend.nexaquiz.dto.response.QuizQuestionResponse;
import com.backend.nexaquiz.dto.response.StartQuizResponse;
import com.backend.nexaquiz.dto.response.SubmitQuizResponse;
import com.backend.nexaquiz.entity.Question;
import com.backend.nexaquiz.entity.Quiz;
import com.backend.nexaquiz.entity.QuizAttempt;
import com.backend.nexaquiz.entity.QuizQuestion;
import com.backend.nexaquiz.entity.StudentAnswer;
import com.backend.nexaquiz.entity.User;
import com.backend.nexaquiz.entity.enums.QuizAttemptStatus;
import com.backend.nexaquiz.exception.ResourceNotFoundException;
import com.backend.nexaquiz.repository.QuestionRepository;
import com.backend.nexaquiz.repository.QuizAttemptRepository;
import com.backend.nexaquiz.repository.QuizQuestionRepository;
import com.backend.nexaquiz.repository.QuizRepository;
import com.backend.nexaquiz.repository.StudentAnswerRepository;
import com.backend.nexaquiz.repository.UserRepository;
import com.backend.nexaquiz.service.QuizAttemptService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuizAttemptServiceImpl implements QuizAttemptService {

    private final QuizRepository quizRepository;

    private final QuizAttemptRepository quizAttemptRepository;

    private final QuizQuestionRepository quizQuestionRepository;

    private final StudentAnswerRepository studentAnswerRepository;

    private final QuestionRepository questionRepository;

    private final UserRepository userRepository;

    @Override
    public StartQuizResponse startQuiz(
            Long quizId,
            Long studentId) {

        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Quiz not found"));

        User student = userRepository.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found"));

        QuizAttempt attempt = QuizAttempt.builder()
                .quiz(quiz)
                .student(student)
                .startTime(LocalDateTime.now())
                .status(QuizAttemptStatus.IN_PROGRESS)
                .score(0)
                .percentage(0.0)
                .build();

        attempt = quizAttemptRepository.save(attempt);

        return StartQuizResponse.builder()
                .attemptId(attempt.getId())
                .quizId(quiz.getId())
                .quizTitle(quiz.getTitle())
                .durationMinutes(quiz.getDurationMinutes())
                .startTime(attempt.getStartTime())
                .totalQuestions(quiz.getTotalMarks())
                .build();
    }

    @Override
    public List<QuizQuestionResponse> getQuestions(
            Long quizId) {

        List<QuizQuestion> quizQuestions =
                quizQuestionRepository
                        .findByQuizIdOrderByQuestionOrder(
                                quizId);

        return quizQuestions.stream()
                .map(qq -> QuizQuestionResponse.builder()
                        .questionId(
                                qq.getQuestion().getId())
                        .questionText(
                                qq.getQuestion().getQuestionText())
                        .optionA(
                                qq.getQuestion().getOptionA())
                        .optionB(
                                qq.getQuestion().getOptionB())
                        .optionC(
                                qq.getQuestion().getOptionC())
                        .optionD(
                                qq.getQuestion().getOptionD())
                        .codeSnippet(
                                qq.getQuestion().getCodeSnippet())
                        .questionNumber(
                                qq.getQuestionOrder())
                        .build())
                .toList();
    }

    @Override
    public void saveAnswer(
            SubmitAnswerRequest request) {

        QuizAttempt attempt =
                quizAttemptRepository
                        .findById(request.getAttemptId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Attempt not found"));

        Question question =
                questionRepository
                        .findById(request.getQuestionId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Question not found"));

        StudentAnswer answer =
                studentAnswerRepository
                        .findByAttemptIdAndQuestionId(
                                request.getAttemptId(),
                                request.getQuestionId())
                        .orElse(
                                StudentAnswer.builder()
                                        .attempt(attempt)
                                        .question(question)
                                        .build());

        answer.setSelectedAnswer(
                request.getSelectedAnswer());

        answer.setCorrect(
                request.getSelectedAnswer()
                        .equalsIgnoreCase(
                                question.getCorrectAnswer()));

        studentAnswerRepository.save(answer);
    }

    @Override
    public SubmitQuizResponse submitQuiz(
            Long attemptId) {

        QuizAttempt attempt =
                quizAttemptRepository
                        .findById(attemptId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Attempt not found"));

        List<StudentAnswer> answers =
                studentAnswerRepository
                        .findByAttemptId(attemptId);

        int totalQuestions =
                attempt.getQuiz().getTotalMarks();

        int correctAnswers =
                (int) answers.stream()
                        .filter(StudentAnswer::getCorrect)
                        .count();

        int wrongAnswers =
                totalQuestions - correctAnswers;

        double percentage =
                totalQuestions == 0
                        ? 0
                        : ((double) correctAnswers
                        / totalQuestions) * 100;

        attempt.setTotalQuestions(
                totalQuestions);

        attempt.setCorrectAnswers(
                correctAnswers);

        attempt.setWrongAnswers(
                wrongAnswers);

        attempt.setScore(
                correctAnswers);

        attempt.setPercentage(
                percentage);

        attempt.setEndTime(
                LocalDateTime.now());

        attempt.setStatus(
                QuizAttemptStatus.SUBMITTED);

        quizAttemptRepository.save(attempt);

        return SubmitQuizResponse.builder()
                .totalQuestions(totalQuestions)
                .correctAnswers(correctAnswers)
                .wrongAnswers(wrongAnswers)
                .score(correctAnswers)
                .percentage(
                        Math.round(percentage * 100.0)
                                / 100.0)
                .build();
    }
}