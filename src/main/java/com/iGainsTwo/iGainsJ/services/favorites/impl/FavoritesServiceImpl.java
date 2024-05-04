package com.iGainsTwo.iGainsJ.services.favorites.impl;

import com.iGainsTwo.iGainsJ.DTO.exercise.AddDelFavoriteExerciseDTO;
import com.iGainsTwo.iGainsJ.DTO.exercise.ExerciseDTO;
import com.iGainsTwo.iGainsJ.exceptions.AlreadyInFavoritesException;
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
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FavoritesServiceImpl implements FavoritesService {
    private final UserRepository userRepository;
    private final ExerciseRepository exerciseRepository;
    private final FavoriteRepository favoriteRepository;

    private final ExerciseMapper exerciseMapper;

    @Override
    @Transactional
    public ExerciseDTO addFavorite(AddDelFavoriteExerciseDTO addDelFavoriteExerciseDTO) throws UserNeverExistedException, ExerciseNeverExistedException, AlreadyInFavoritesException {
        Optional<User> userOptional = userRepository.findByEmail(addDelFavoriteExerciseDTO.email());
        User user = userOptional.orElseThrow(() -> new UserNeverExistedException("This user doesn't exist"));

        Optional<Exercise> exerciseOptional = exerciseRepository.findById(addDelFavoriteExerciseDTO.exerciseId());
        Exercise exercise = exerciseOptional.orElseThrow(() -> new ExerciseNeverExistedException("This exercise doesn't exist"));

        List<Favorite> favoriteList = user.getFavorites();
        Long exerciseId = addDelFavoriteExerciseDTO.exerciseId();

        boolean isExerciseAlreadyInFavorites = favoriteList.stream()
                .map(Favorite::getExercise)
                .anyMatch(exerc -> exerc.getId().equals(exerciseId));

        if (isExerciseAlreadyInFavorites) {
            throw new AlreadyInFavoritesException("This exercise already in the list");
        }

        Favorite favorite = new Favorite();
        favorite.setExercise(exercise);
        favorite.setUser(user);
        userRepository.save(user);
        favoriteRepository.save(favorite);
        return exerciseMapper.toDto(exercise);
    }

    @Override
    @Transactional
    public void deleteFavorite(AddDelFavoriteExerciseDTO addDelFavoriteExerciseDTO) throws UserNeverExistedException, FavoriteNeverExistedException {
        Optional<User> userOptional = userRepository.findByEmail(addDelFavoriteExerciseDTO.email());
        User user = userOptional.orElseThrow(() -> new UserNeverExistedException("This user doesn't exist"));

        Optional<Favorite> favoriteOptional = favoriteRepository.findByExerciseId(addDelFavoriteExerciseDTO.exerciseId());
        Favorite favorite = favoriteOptional.orElseThrow(() -> new FavoriteNeverExistedException("User doesn't have favorite exercise like this"));

        user.getFavorites().remove(favorite);
        favoriteRepository.delete(favorite);
    }
}