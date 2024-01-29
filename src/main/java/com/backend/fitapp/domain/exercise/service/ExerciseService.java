package com.backend.fitapp.domain.exercise.service;

import com.backend.fitapp.domain.exercise.model.Exercise;

import java.util.List;

public interface ExerciseService {
    Exercise saveExercise(Exercise exercise);

    void saveExerciseList(List<Exercise> exerciseList);

    List<Exercise> getAllExercises();
}
