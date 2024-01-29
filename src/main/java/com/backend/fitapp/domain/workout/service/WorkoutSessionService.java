package com.backend.fitapp.domain.workout.service;

import com.backend.fitapp.domain.workout.model.WorkoutSession;

import java.util.Optional;

public interface WorkoutSessionService {
    WorkoutSession createWorkoutSession(Long workoutId, WorkoutSession session);

    Optional<WorkoutSession> getById(Long sessionId);
}
