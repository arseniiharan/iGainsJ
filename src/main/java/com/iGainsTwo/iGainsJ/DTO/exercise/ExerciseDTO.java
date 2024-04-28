package com.iGainsTwo.iGainsJ.DTO.exercise;

import lombok.Data;

@Data
public class ExerciseDTO {
    private String exerciseCategory;
    private String exerciseImage;
    private String exerciseTitle;
    private int exerciseDuration;
    private int exerciseKcal;
}
