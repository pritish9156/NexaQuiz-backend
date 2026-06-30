package com.backend.nexaquiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.nexaquiz.entity.QuizQuestion;

import java.util.List;

public interface QuizQuestionRepository extends JpaRepository<QuizQuestion, Long> {

    List<QuizQuestion> findByQuizIdOrderByQuestionOrder(Long quizId);

    List<QuizQuestion> findByQuizId(Long quizId);

    List<QuizQuestion>
    findByQuizIdOrderByQuestionOrderAsc(
            Long quizId
    );
}