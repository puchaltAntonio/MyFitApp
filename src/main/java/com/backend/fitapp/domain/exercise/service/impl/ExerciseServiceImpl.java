package com.backend.fitapp.domain.exercise.service.impl;

import com.backend.fitapp.domain.exercise.model.Exercise;
import com.backend.fitapp.domain.exercise.repository.ExerciseRepository;
import com.backend.fitapp.domain.exercise.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Override
    public Exercise saveExercise(Exercise exercise) {
      return  exerciseRepository.saveAndFlush(exercise);
    }

    @Override
    public void saveExerciseList(List<Exercise> exerciseList) {
        exerciseRepository.saveAllAndFlush(exerciseList);
    }

    @Override
    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }
}
