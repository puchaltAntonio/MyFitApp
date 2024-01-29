package com.backend.fitapp.domain.exercise.repository;

import com.backend.fitapp.domain.exercise.model.ExerciseSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ExerciseSetRepository extends JpaRepository<ExerciseSet, Long> {
}