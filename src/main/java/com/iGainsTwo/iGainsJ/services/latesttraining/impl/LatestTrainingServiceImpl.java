package com.iGainsTwo.iGainsJ.services.latesttraining.impl;

import com.iGainsTwo.iGainsJ.mappers.ExerciseMapper;
import com.iGainsTwo.iGainsJ.models.Exercise;
import com.iGainsTwo.iGainsJ.models.LatestTraining;
import com.iGainsTwo.iGainsJ.models.User;
import com.iGainsTwo.iGainsJ.repositories.ExerciseRepository;
import com.iGainsTwo.iGainsJ.repositories.UserRepository;
import com.iGainsTwo.iGainsJ.services.latesttraining.LatestTrainingService;
import com.iGainsTwo.iGainsJ.DTO.exercise.AddLastTrainingDTO;
import com.iGainsTwo.iGainsJ.DTO.exercise.ExerciseDTO;
import com.iGainsTwo.iGainsJ.exceptions.ExerciseNeverExistedException;
import com.iGainsTwo.iGainsJ.exceptions.UserNeverExistedException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LatestTrainingServiceImpl implements LatestTrainingService {
    private final UserRepository userRepository;
    private final ExerciseRepository exerciseRepository;

    private final ExerciseMapper exerciseMapper;

    @Override
    @Transactional
    public ExerciseDTO addLastTraining(AddLastTrainingDTO addLastTrainingDTO) throws UserNeverExistedException, ExerciseNeverExistedException {
        Optional<User> userOptional = userRepository.findByEmail(addLastTrainingDTO.email());
        User user = userOptional.orElseThrow(() -> new UserNeverExistedException("This user doesn't exist"));

        Optional<Exercise> exerciseOptional = exerciseRepository.findById(addLastTrainingDTO.exerciseId());
        Exercise exercise = exerciseOptional.orElseThrow(() -> new ExerciseNeverExistedException("This exercise doesn't exist"));

        LatestTraining latestTraining = new LatestTraining();
        latestTraining.setUser(user);
        latestTraining.setExercise(exercise);
        latestTraining.setTrainingDate(String.valueOf(new Date()));
        latestTraining.setTrainingTime(exercise.getExerciseDuration());
        user.getLatestTrainings().add(latestTraining);
        userRepository.save(user);
        return exerciseMapper.toDto(exercise);
    }
}
