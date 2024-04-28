package com.iGainsTwo.iGainsJ.mappers;

import com.iGainsTwo.iGainsJ.DTO.exercise.ExerciseDTO;
import com.iGainsTwo.iGainsJ.models.Exercise;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ExerciseMapper {
    @Mapping(target = "exerciseCategory", source = "dto.exerciseCategory")
    @Mapping(target = "exerciseImage", source = "dto.exerciseImage")
    @Mapping(target = "exerciseTitle", source = "dto.exerciseTitle")
    @Mapping(target = "exerciseDuration", source = "dto.exerciseDuration")
    @Mapping(target = "exerciseKcal", source = "dto.exerciseKcal")
    Exercise toModel(ExerciseDTO dto);

    @Mapping(target = "exerciseCategory", source = "exercise.exerciseCategory")
    @Mapping(target = "exerciseImage", source = "exercise.exerciseImage")
    @Mapping(target = "exerciseTitle", source = "exercise.exerciseTitle")
    @Mapping(target = "exerciseDuration", source = "exercise.exerciseDuration")
    @Mapping(target = "exerciseKcal", source = "exercise.exerciseKcal")
    ExerciseDTO toDto(Exercise exercise);
}
