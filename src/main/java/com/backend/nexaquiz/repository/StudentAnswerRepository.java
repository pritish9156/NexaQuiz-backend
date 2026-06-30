package com.backend.nexaquiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.nexaquiz.entity.StudentAnswer;

import java.util.List;
import java.util.Optional;

public interface StudentAnswerRepository extends JpaRepository<StudentAnswer, Long> {

    Optional<StudentAnswer>
    findByAttemptIdAndQuestionId(
            Long attemptId,
            Long questionId);

    List<StudentAnswer>
    findByAttemptId(Long attemptId);

}