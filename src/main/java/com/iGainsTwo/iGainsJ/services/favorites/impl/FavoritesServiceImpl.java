package com.iGainsTwo.iGainsJ.services.favorites.impl;

import com.iGainsTwo.iGainsJ.DTO.exercise.AddDelFavoriteExerciseDTO;
import com.iGainsTwo.iGainsJ.DTO.exercise.ExerciseDTO;
import com.iGainsTwo.iGainsJ.exceptions.ExerciseNeverExistedException;
import com.iGainsTwo.iGainsJ.exceptions.FavoriteNeverExistedException;
import com.iGainsTwo.iGainsJ.exceptions.UserNeverExistedException;
import com.iGainsTwo.iGainsJ.mappers.ExerciseMapper;
import com.iGainsTwo.iGainsJ.models.Exercise;
import com.iGainsTwo.iGainsJ.models.Favorite;
import com.iGainsTwo.iGainsJ.models.User;
import com.iGainsTwo.iGainsJ.repositories.ExerciseRepository;
import com.iGainsTwo.iGainsJ.repositories.FavoriteRepository;
import com.iGainsTwo.iGainsJ.repositories.UserRepository;
import com.iGainsTwo.iGainsJ.services.favorites.FavoritesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FavoritesServiceImpl implements FavoritesService {
    private final UserRepository userRepository;
    private final ExerciseRepository exerciseRepository;
    private final FavoriteRepository favoriteRepository;

    private final ExerciseMapper exerciseMapper;

    @Override
    public ExerciseDTO addFavorite(AddDelFavoriteExerciseDTO addDelFavoriteExerciseDTO) throws UserNeverExistedException, ExerciseNeverExistedException {
        Optional<User> userOptional = userRepository.findById(addDelFavoriteExerciseDTO.userId());
        if (userOptional.isEmpty()) {
            throw new UserNeverExistedException("This user doesn't exist");
        }
        Optional<Exercise> exerciseOptional = exerciseRepository.findById(addDelFavoriteExerciseDTO.exerciseId());
        if (exerciseOptional.isEmpty()) {
            throw new ExerciseNeverExistedException("This exercise doesn't exist");
        }

        Exercise exercise = exerciseOptional.get();
        User user = userOptional.get();
        Favorite favorite = new Favorite();
        favorite.setExercise(exercise);
        favorite.setUser(user);
        userRepository.save(user);
        favoriteRepository.save(favorite);
        return exerciseMapper.toDto(exercise);
    }

    @Override
    public void deleteFavorite(AddDelFavoriteExerciseDTO addDelFavoriteExerciseDTO) throws UserNeverExistedException, FavoriteNeverExistedException {
        Optional<User> userOptional = userRepository.findById(addDelFavoriteExerciseDTO.userId());
        if (userOptional.isEmpty()) {
            throw new UserNeverExistedException("User with that email doesn't exist");
        }

        Optional<Favorite> favoriteOptional = favoriteRepository.findByExerciseId(addDelFavoriteExerciseDTO.exerciseId());
        if (favoriteOptional.isEmpty()) {
            throw new FavoriteNeverExistedException("User doesn't have favorite exercise like this");
        }
        Favorite favorite = favoriteOptional.get();
        User user = userOptional.get();
        user.getFavorites().remove(favorite);
        favoriteRepository.delete(favorite);
    }
}