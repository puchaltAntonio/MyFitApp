package com.backend.fitapp.application.controller;

import com.backend.fitapp.domain.workout.model.Workout;
import com.backend.fitapp.domain.user.repository.UserRepository;
import com.backend.fitapp.domain.workout.service.impl.WorkoutServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/workout")
public class WorkoutController {

    @Autowired
    private WorkoutServiceImpl workoutService;

    @GetMapping("/all")
    public @ResponseBody ResponseEntity<List<Workout>> getAllWorkouts(){
        List<Workout> workoutList = new ArrayList<>(workoutService.findAll());

        if(workoutList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Workout>>(workoutService.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/users/{userId}/workouts")
    public @ResponseBody ResponseEntity<List<Workout>> getUserWorkouts (@PathVariable Long userId){
        return new ResponseEntity<>(workoutService.findAllByUserId(userId), HttpStatus.OK);
    }

    @PostMapping(path = "/users/{userId}/workouts")
    public @ResponseBody ResponseEntity<Workout> addNewWorkout (@PathVariable Long userId, @RequestBody Workout workout) {
        Workout newWorkout = workoutService.addWorkoutToUser(workout, userId);
        if (newWorkout != null) {
            return new ResponseEntity<>(newWorkout, HttpStatus.CREATED);
        }
        return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @PutMapping(path = "/workouts/{workoutId}")
    public @ResponseBody ResponseEntity<Workout> modifyWorkout(@PathVariable Long workoutId, @RequestBody Workout workout){
        return new ResponseEntity<>(workoutService.modifyWorkout(workout, workoutId), HttpStatus.OK);
    }
}
