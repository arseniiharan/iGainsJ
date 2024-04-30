package com.iGainsTwo.iGainsJ.services.exercise.impl;

import com.iGainsTwo.iGainsJ.DTO.exercise.ExerciseDTO;
import com.iGainsTwo.iGainsJ.exceptions.ExerciseNeverExistedException;
import com.iGainsTwo.iGainsJ.mappers.ExerciseMapper;
import com.iGainsTwo.iGainsJ.models.Exercise;
import com.iGainsTwo.iGainsJ.repositories.ExerciseRepository;
import com.iGainsTwo.iGainsJ.services.exercise.ExerciseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExerciseServiceImpl implements ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final ExerciseMapper exerciseMapper;

    @Override
    @Transactional
    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }

    @Override
    @Transactional
    public ExerciseDTO getExerciseById(Long id) throws ExerciseNeverExistedException {
        Optional<Exercise> exerciseOptional = exerciseRepository.findById(id);
        Exercise exercise = exerciseOptional.orElseThrow(() -> new ExerciseNeverExistedException("This exercise doesn't exist"));

        return exerciseMapper.toDto(exercise);
    }
}