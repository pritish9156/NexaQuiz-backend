package com.backend.nexaquiz.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "quiz_answers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizAnswer extends BaseEntity {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "attempt_id",
            nullable = false
    )
    private QuizAttempt attempt;

    @ManyToOne
    @JoinColumn(
            name = "question_id",
            nullable = false
    )
    private Question question;

    @Column(nullable = false)
    private String selectedAnswer;

    @Column(nullable = false)
    private String correctAnswer;

    @Column(nullable = false)
    private Boolean correct;
}