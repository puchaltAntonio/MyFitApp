package com.backend.fitapp.domain.workout.service.impl;

import com.backend.fitapp.domain.workout.model.Workout;
import com.backend.fitapp.domain.exercise.model.Exercise;
import com.backend.fitapp.domain.exercise.repository.ExerciseRepository;
import com.backend.fitapp.domain.user.repository.UserRepository;
import com.backend.fitapp.domain.workout.repository.WorkoutRepository;
import com.backend.fitapp.domain.workout.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class WorkoutServiceImpl implements WorkoutService {
    @Autowired
    private WorkoutRepository workoutRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ExerciseRepository exerciseRepository;

    @Override
    public Workout addWorkoutToUser(Workout newWorkout, Long userId) {
        Workout workout = userRepository.findById(userId).map(user -> {
            newWorkout.setUser(user);
            return newWorkout;
        }).orElseThrow(() -> new RuntimeException("User not found"));

        Set<Exercise> exerciseSet = new HashSet<>();
        if(workout.getExerciseSet() != null){
            workout.getExerciseSet().forEach(exercise -> {
            Optional<Exercise> exerciseOptional = exerciseRepository.findById(exercise.getId());
            exerciseOptional.ifPresent(exerciseSet::add);

            });

            if(exerciseSet.isEmpty() || exerciseSet.size() != workout.getExerciseSet().size()){
                return null;
            }
            newWorkout.setExerciseSet(exerciseSet);

            return workoutRepository.saveAndFlush(newWorkout);

        }
        return null;
    }

    @Override
    public Workout modifyWorkout(Workout modificatedWorkout, Long workoutId) {
        Optional<Workout> optionalWorkout = workoutRepository.findById(workoutId);
        if(optionalWorkout.isPresent()){
            Workout workout = optionalWorkout.get();

            workout.setName(modificatedWorkout.getName());
            return workoutRepository.saveAndFlush(workout);
        }
        return null;
    }

    @Override
    public Workout findWorkoutById(Long id) {
        return workoutRepository.findById(id).orElse(null);
    }


    @Override
    public List<Workout> findAll() {
        return workoutRepository.findAll();
    }

    @Override
    public List<Workout> findAllByUserId(Long userId) {
        return workoutRepository.findAllByUserId(userId);
    }


}
