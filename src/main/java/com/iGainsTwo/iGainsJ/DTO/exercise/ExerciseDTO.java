package com.iGainsTwo.iGainsJ.DTO.exercise;

import lombok.Data;

@Data
public class ExerciseDTO {
    private Long id;
    private String exerciseCategory;
    private String exerciseImage;
    private String exerciseTitle;
    private int exerciseDuration;
    private int exerciseKcal;
    private boolean exerciseSelected;
}
