package com.backend.nexaquiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.nexaquiz.entity.QuizAttempt;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface QuizAttemptRepository extends JpaRepository<QuizAttempt, Long> {

    List<QuizAttempt>
    findByStudentId(Long studentId);

    Optional<QuizAttempt> findByIdAndStudentId(
            Long attemptId,
            Long studentId);

    List<QuizAttempt> findByStudentIdOrderByCreatedAtDesc(
            Long studentId);

    @Query("""
       SELECT AVG(q.percentage)
       FROM QuizAttempt q
       WHERE q.percentage IS NOT NULL
       """)
    Double getAverageScore();

    boolean existsByQuizIdAndStudentId(
            Long quizId,
            Long studentId
    );

    List<QuizAttempt> findTop5ByOrderByCreatedAtDesc();

    @Query("""
    SELECT qa.student.id,
           CONCAT(
               qa.student.firstName,
               ' ',
               qa.student.lastName
           ),
           AVG(qa.percentage)
    FROM QuizAttempt qa
    GROUP BY qa.student.id,
             qa.student.firstName,
             qa.student.lastName
    ORDER BY AVG(qa.percentage) DESC
    """)
    List<Object[]> getTopStudents();

    @Query("""
    SELECT
    MONTH(q.createdAt),
    AVG(q.percentage)
    FROM QuizAttempt q
    GROUP BY MONTH(q.createdAt)
    ORDER BY MONTH(q.createdAt)
    """)
    List<Object[]> getMonthlyPerformance();

    @Query("""
    SELECT
    COUNT(qa),
    AVG(qa.percentage),
    MAX(qa.percentage),
    MIN(qa.percentage)
    FROM QuizAttempt qa
    WHERE qa.quiz.id = :quizId
    """)
    List<Object[]> getQuizAnalytics(
            Long quizId
    );

    List<QuizAttempt>
    findByQuizIdOrderByCreatedAtDesc(
            Long quizId
    );

    List<QuizAttempt> findAllByOrderByCreatedAtDesc();

}