package com.backend.fitapp.domain.service.impl;

import com.backend.fitapp.domain.model.Workout;
import com.backend.fitapp.domain.repository.UserRepository;
import com.backend.fitapp.domain.repository.WorkoutRepository;
import com.backend.fitapp.domain.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkoutServiceImpl implements WorkoutService {
    @Autowired
    private WorkoutRepository workoutRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Workout addWorkoutToUser(Workout newWorkout, Long userId) {

        return userRepository.findById(userId).map(user -> {
            newWorkout.setUser(user);
            return workoutRepository.saveAndFlush(newWorkout);
        }).orElseThrow(() -> new RuntimeException("User not found"));
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
