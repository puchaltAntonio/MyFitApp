package com.backend.fitapp.application.controller;

import com.backend.fitapp.domain.workout.model.WorkoutSession;
import com.backend.fitapp.domain.workout.service.impl.WorkoutSessionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/workout_session")
public class WorkoutSessionController {

    @Autowired
    private WorkoutSessionServiceImpl workoutSessionService;

    @PostMapping(path = "/{workoutId}")
    public @ResponseBody ResponseEntity<WorkoutSession> createWorkoutSession (@PathVariable Long workoutId, @RequestBody WorkoutSession workoutSession) {
        WorkoutSession newWorkoutSession = workoutSessionService.createWorkoutSession(workoutId,workoutSession);
        if (newWorkoutSession != null) {
            return new ResponseEntity<>(newWorkoutSession, HttpStatus.CREATED);
        }
        return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = "/{workout_session_id}")
    public @ResponseBody ResponseEntity<WorkoutSession> getWorkoutSession (@PathVariable Long workout_session_id) {
        Optional<WorkoutSession> sessionOptional = workoutSessionService.getById(workout_session_id);

        return sessionOptional.map(session -> new ResponseEntity<>(session, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }


}
