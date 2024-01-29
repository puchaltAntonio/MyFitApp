package com.backend.fitapp.domain.workout.service.impl;

import com.backend.fitapp.domain.exercise.model.ExerciseRecord;
import com.backend.fitapp.domain.exercise.repository.ExerciseRecordRepository;
import com.backend.fitapp.domain.workout.model.WorkoutSession;
import com.backend.fitapp.domain.workout.repository.WorkoutRepository;
import com.backend.fitapp.domain.workout.repository.WorkoutSessionRepository;
import com.backend.fitapp.domain.workout.service.WorkoutSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class WorkoutSessionServiceImpl implements WorkoutSessionService {

    @Autowired
    private WorkoutSessionRepository workoutSessionRepository;

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private ExerciseRecordRepository exerciseRecordRepository;


    @Override
    public WorkoutSession createWorkoutSession(Long workoutId, WorkoutSession sessionRequest) {
        // Search For Workout and set it up in the workoutSession
        WorkoutSession workoutSession = workoutRepository.findById(workoutId).map(workout -> {
           sessionRequest.setWorkout(workout);
           return sessionRequest;
        }).orElseThrow( () -> new RuntimeException("Workout Not Found"));


       Duration duration = Duration.between(workoutSession.getStartedTimeStamp(), workoutSession.getFinishedTimeStamp());

       workoutSession.setDuration(duration);

       WorkoutSession session = new WorkoutSession();

       List<ExerciseRecord> exerciseRecordList = workoutSession.getExerciseRecords();

       List<ExerciseRecord> exerciseRecordList1 = new ArrayList<>();
       exerciseRecordList.forEach(record -> {
           ExerciseRecord newRecord = new ExerciseRecord();
           newRecord.setWorkoutSession(session);
           newRecord.setExercise(record.getExercise());
           newRecord.setTimeStamp(record.getTimeStamp());
           newRecord.setExerciseSetList(record.getExerciseSetList());
           exerciseRecordList1.add(newRecord);
       });


       session.setWorkout(workoutSession.getWorkout());
       session.setDuration(workoutSession.getDuration());
       session.setVolume(workoutSession.getVolume());
       session.setStartedTimeStamp(workoutSession.getStartedTimeStamp());
       session.setFinishedTimeStamp(workoutSession.getFinishedTimeStamp());
       session.setTotalReps(workoutSession.getTotalReps());
       session.setExerciseRecords(exerciseRecordList1);



       WorkoutSession savedWorkoutSession = workoutSessionRepository.saveAndFlush(session);

//       exerciseRecordList.forEach(record -> {
//           record.setWorkoutSession(workoutSession);
//           exerciseRecordRepository.saveAndFlush(record);
//       });


       return workoutSessionRepository.findById(savedWorkoutSession.getId()).orElse(null);


    }

    @Override
    public Optional<WorkoutSession> getById(Long sessionId) {
        return workoutSessionRepository.findById(sessionId);
    }
}
