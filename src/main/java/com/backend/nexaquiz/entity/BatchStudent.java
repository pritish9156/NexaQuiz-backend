package com.backend.nexaquiz.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "batch_students",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {
                                "batch_id",
                                "student_id"
                        }
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BatchStudent {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "batch_id",
            nullable = false
    )
    private Batch batch;

    @ManyToOne
    @JoinColumn(
            name = "student_id",
            nullable = false
    )
    private User student;
}