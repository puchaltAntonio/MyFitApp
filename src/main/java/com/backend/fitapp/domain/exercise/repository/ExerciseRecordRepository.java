package com.backend.fitapp.domain.exercise.repository;

import com.backend.fitapp.domain.exercise.model.ExerciseRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRecordRepository extends JpaRepository<ExerciseRecord, Long> {
}