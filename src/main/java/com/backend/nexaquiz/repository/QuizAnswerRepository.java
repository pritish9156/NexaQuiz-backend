package com.backend.nexaquiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.nexaquiz.entity.QuizAnswer;

public interface QuizAnswerRepository
        extends JpaRepository<
        QuizAnswer,
        Long> {

    List<QuizAnswer>
    findByAttemptId(
            Long attemptId
    );

}