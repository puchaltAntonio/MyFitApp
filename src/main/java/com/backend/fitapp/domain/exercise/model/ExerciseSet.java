package com.backend.fitapp.domain.exercise.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name="exercise_sets")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ExerciseSet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private Integer setPosition;

    private Double weight;

    private Integer numberOfReps;



    @ManyToOne
    @JoinColumn(name = "exercise_record_id", nullable = false)
    private ExerciseRecord exerciseRecord;

}
