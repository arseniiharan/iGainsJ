package com.iGainsTwo.iGainsJ.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name="exercises")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="exercise_category", nullable = false)
    private String exerciseCategory;

    @Column(name="exercise_image")
    private String exerciseImage;

    @Column(name="exercise_title")
    private String exerciseTitle;

    @Column(name="exercise_duration", nullable = false)
    private int exerciseDuration;

    @Column(name="exercise_kcal", nullable = false)
    private int exerciseKcal;

    @Column(name="exercise_select", nullable = false)
    @ColumnDefault("false")
    private boolean exerciseSelected;

    @JsonIgnore
    @OneToMany(mappedBy="exercise", cascade=CascadeType.ALL)
    private List<Favorite> favorites;

    @JsonIgnore
    @OneToMany(mappedBy ="exercise", cascade=CascadeType.ALL)
    private List<LatestTraining> latestTrainings;

    @JsonIgnore
    @OneToMany(mappedBy="exercise", cascade=CascadeType.ALL)
    private List<UserCalendar> userCalendars;

}
