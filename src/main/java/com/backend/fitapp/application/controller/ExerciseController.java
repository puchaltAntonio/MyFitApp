package com.backend.fitapp.application.controller;

import com.backend.fitapp.domain.exercise.model.Exercise;
import com.backend.fitapp.domain.exercise.service.impl.ExerciseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/exercises")
public class ExerciseController {

    @Autowired
    private ExerciseServiceImpl exerciseService;

    @PostMapping(path = "/")
    public @ResponseBody ResponseEntity<Exercise> addExercise (@RequestBody Exercise exercise) {
        Exercise newExercise = exerciseService.saveExercise(exercise);
        return new ResponseEntity<Exercise>(newExercise, HttpStatus.OK);
    }

    //TODO: CHANGE Return type
    @PostMapping(path = "/list")
    public @ResponseBody ResponseEntity<Exercise> addExerciseList (@RequestBody List<Exercise> exerciseList) {
        exerciseService.saveExerciseList(exerciseList);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/all")
    public @ResponseBody ResponseEntity<List<Exercise>> getAllExercises(){
        List<Exercise> exerciseList = exerciseService.getAllExercises();
        return new ResponseEntity<List<Exercise>>(exerciseList, HttpStatus.OK);
    }


}
