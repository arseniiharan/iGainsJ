package com.iGainsTwo.iGainsJ.services.favorites;

import com.iGainsTwo.iGainsJ.DTO.exercise.AddDelFavoriteExerciseDTO;
import com.iGainsTwo.iGainsJ.DTO.exercise.AddLastTrainingDTO;
import com.iGainsTwo.iGainsJ.DTO.exercise.ExerciseDTO;
import com.iGainsTwo.iGainsJ.exceptions.ExerciseNeverExistedException;
import com.iGainsTwo.iGainsJ.exceptions.FavoriteNeverExistedException;
import com.iGainsTwo.iGainsJ.exceptions.UserNeverExistedException;

public interface FavoritesService {
    ExerciseDTO addFavorite(AddDelFavoriteExerciseDTO addDelFavoriteExerciseDTO) throws UserNeverExistedException, ExerciseNeverExistedException;
    void deleteFavorite(AddDelFavoriteExerciseDTO addDelFavoriteExerciseDTO) throws UserNeverExistedException, FavoriteNeverExistedException;
}
