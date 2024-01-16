package com.backend.fitapp.domain.service;

import com.backend.fitapp.domain.model.Workout;

import java.util.List;

public interface WorkoutService {


    Workout addWorkoutToUser(Workout workout, Long userId);
    Workout modifyWorkout(Workout modificatedWorkout, Long workoutId);
    Workout findWorkoutById(Long id);

    List<Workout> findAll();

    List<Workout> findAllByUserId(Long userId);

}
