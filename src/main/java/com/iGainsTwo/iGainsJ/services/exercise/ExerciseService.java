package com.iGainsTwo.iGainsJ.services.exercise;

import com.iGainsTwo.iGainsJ.DTO.exercise.ExerciseDTO;
import com.iGainsTwo.iGainsJ.exceptions.ExerciseNeverExistedException;
import com.iGainsTwo.iGainsJ.models.Exercise;

import java.util.List;

public interface ExerciseService {
    List<Exercise> getAllExercises();
    ExerciseDTO getExerciseById(Long id) throws ExerciseNeverExistedException;
}
