package com.iGainsTwo.iGainsJ.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name="exercises")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "exercise_category", nullable = false)
    @NotBlank
    private String exerciseCategory;

    @Column(name = "exercise_image")
    private String exerciseImage;

    @Column(name = "exercise_title")
    @NotBlank
    private String exerciseTitle;

    @Column(name = "exercise_duration", nullable = false)
    @NotBlank
    private int exerciseDuration;

    @Column(name = "exercise_kcal", nullable = false)
    @NotBlank
    private int exerciseKcal;

    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL)
    private List<Favorite> favorites;

    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL)
    private List<LatestTraining> latestTrainings;

    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL)
    private List<UserCalendar> userCalendars;

}
