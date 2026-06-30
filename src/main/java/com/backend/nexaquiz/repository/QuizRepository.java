package com.backend.nexaquiz.repository;

import com.backend.nexaquiz.entity.enums.QuizStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.nexaquiz.entity.Quiz;

import java.time.LocalDateTime;
import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

    List<Quiz> findByStatus(QuizStatus status);

    List<Quiz> findByStatusAndScheduledAtBetween(
            QuizStatus status,
            LocalDateTime start,
            LocalDateTime end);


    List<Quiz> findByBatchIdAndActiveTrue(
            Long batchId);

    List<Quiz>
    findByBatch_IdAndStatusAndActiveTrue(
            Long batchId,
            QuizStatus status
    );

    List<Quiz>
    findByBatch_IdAndStatusAndActiveTrueAndScheduledAtBetween(

            Long batchId,

            QuizStatus status,

            LocalDateTime start,

            LocalDateTime end

    );

    List<Quiz>
    findTop5ByStatusAndScheduledAtAfterOrderByScheduledAtAsc(

            QuizStatus status,

            LocalDateTime scheduledAt

    );

    List<Quiz>
    findByBatch_IdInAndStatusAndActiveTrueAndScheduledAtGreaterThanEqual(

            List<Long> batchIds,

            QuizStatus status,

            LocalDateTime dateTime

    );

}