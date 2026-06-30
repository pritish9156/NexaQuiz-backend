package com.backend.nexaquiz.serviceImpl;

import com.backend.nexaquiz.dto.response.*;
import com.backend.nexaquiz.entity.enums.QuizStatus;
import com.backend.nexaquiz.entity.enums.Role;
import org.springframework.stereotype.Service;

import com.backend.nexaquiz.repository.*;
import com.backend.nexaquiz.service.DashboardService;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl
        implements DashboardService {

    private final UserRepository userRepository;

    private final SubjectRepository subjectRepository;

    private final TopicRepository topicRepository;

    private final QuestionRepository questionRepository;

    private final QuizRepository quizRepository;

    private final QuizAttemptRepository
            quizAttemptRepository;

    @Override
    public DashboardResponse
    getDashboardData() {

        return DashboardResponse
                .builder()

                .totalStudents(

                        userRepository.countByRole(
                                Role.STUDENT
                        )

                )

                .totalSubjects(

                        subjectRepository.count()

                )

                .totalTopics(

                        topicRepository.count()

                )

                .totalQuestions(

                        questionRepository.count()

                )

                .totalQuizzes(

                        quizRepository.count()

                )

                .averageScore(

                        quizAttemptRepository
                                .getAverageScore()

                )

                .build();
    }

    @Override
    public List<RecentAttemptResponse>
    getRecentAttempts() {

        return quizAttemptRepository

                .findTop5ByOrderByCreatedAtDesc()

                .stream()

                .map(attempt ->

                        RecentAttemptResponse
                                .builder()

                                .studentName(

                                        attempt.getStudent()
                                                .getFirstName()

                                )

                                .quizTitle(

                                        attempt.getQuiz()
                                                .getTitle()

                                )

                                .score(
                                        attempt.getScore()
                                )

                                .percentage(
                                        attempt.getPercentage()
                                )

                                .build()

                )

                .toList();
    }

    @Override
    public List<UpcomingQuizResponse>
    getUpcomingQuizzes() {

        return quizRepository

                .findTop5ByStatusAndScheduledAtAfterOrderByScheduledAtAsc(

                        QuizStatus.PUBLISHED,

                        LocalDateTime.now()

                )

                .stream()

                .map(quiz ->

                        UpcomingQuizResponse
                                .builder()

                                .title(
                                        quiz.getTitle()
                                )

                                .subjectName(
                                        quiz.getSubject()
                                                .getName()
                                )

                                .scheduledAt(
                                        quiz.getScheduledAt()
                                )

                                .build()

                )

                .toList();
    }

    @Override
    public List<TopStudentResponse>
    getTopStudents() {

        return quizAttemptRepository

                .getTopStudents()

                .stream()

                .limit(5)

                .map(row ->

                        TopStudentResponse
                                .builder()

                                .studentId(
                                        (Long) row[0]
                                )

                                .studentName(
                                        (String) row[1]
                                )

                                .averagePercentage(
                                        (Double) row[2]
                                )

                                .build()

                )

                .toList();
    }

    @Override
    public List<MonthlyPerformanceResponse>
    getMonthlyPerformance() {

        String[] months = {

                "",

                "Jan",

                "Feb",

                "Mar",

                "Apr",

                "May",

                "Jun",

                "Jul",

                "Aug",

                "Sep",

                "Oct",

                "Nov",

                "Dec"

        };

        return quizAttemptRepository

                .getMonthlyPerformance()

                .stream()

                .map(row ->

                        MonthlyPerformanceResponse

                                .builder()

                                .month(

                                        months[
                                                ((Number)row[0])
                                                        .intValue()
                                                ]

                                )

                                .score(

                                        ((Number)row[1])
                                                .doubleValue()

                                )

                                .build()

                )

                .toList();
    }
}