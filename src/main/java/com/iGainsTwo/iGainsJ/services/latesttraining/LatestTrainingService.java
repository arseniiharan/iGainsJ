package com.iGainsTwo.iGainsJ.services.latesttraining;

import com.iGainsTwo.iGainsJ.DTO.exercise.AddLastTrainingDTO;
import com.iGainsTwo.iGainsJ.DTO.exercise.ExerciseDTO;
import com.iGainsTwo.iGainsJ.exceptions.ExerciseNeverExistedException;
import com.iGainsTwo.iGainsJ.exceptions.UserNeverExistedException;

public interface LatestTrainingService {
    ExerciseDTO addLastTraining(AddLastTrainingDTO addLastTrainingDTO) throws UserNeverExistedException, ExerciseNeverExistedException;
}
