package com.backend.fitapp.domain.workout.model;

import com.backend.fitapp.domain.exercise.model.ExerciseRecord;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name="workout_session")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WorkoutSession {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private Double volume;

    private Integer totalReps;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startedTimeStamp;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime finishedTimeStamp;

    private Duration duration;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "workout_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    @ToString.Exclude
    private Workout workout;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "workoutSession")
    @ToString.Exclude
    List<ExerciseRecord> exerciseRecords;

}
