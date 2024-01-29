package com.backend.fitapp.domain.exercise.model;

import com.backend.fitapp.domain.workout.model.WorkoutSession;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="exercise_records")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ExerciseRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timeStamp;

    @ManyToOne
    @JoinColumn(name = "workout_session_id", nullable = false)
    private WorkoutSession workoutSession;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    @OneToMany(mappedBy = "exerciseRecord")
    @ToString.Exclude
    private List<ExerciseSet> exerciseSetList;



}
