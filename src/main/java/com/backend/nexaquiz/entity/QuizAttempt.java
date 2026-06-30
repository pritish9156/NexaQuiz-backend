package com.backend.nexaquiz.entity;

import java.time.LocalDateTime;

import com.backend.nexaquiz.entity.enums.QuizAttemptStatus;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "quiz_attempts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizAttempt extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Integer score;

    private Double percentage;

    private Integer totalQuestions;

    private Integer correctAnswers;

    private Integer wrongAnswers;

    @Enumerated(EnumType.STRING)
    private QuizAttemptStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;
}