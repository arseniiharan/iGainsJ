package com.iGainsTwo.iGainsJ.models;

import jakarta.persistence.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id", unique = true)
    private UUID id;

    @Column(name="password", nullable = false)
    @NotBlank
    private String password;

    @Email
    @Column(name="email", nullable = false, unique = true)
    @NotBlank
    private String email;

    @Column(name="user_name", nullable = false)
    @NotBlank
    private String userName;

    @Column(name="age", nullable = false)
    private int age;

    @Column(name="gender",nullable = false)
    private int gender;

    @Column(name="weight", nullable = false)
    private int weight;

    @Column(name="height", nullable = false)
    private int height;

    @Column(name="photo")
    private String photo;

    @Column(name="kcal_count")
    private int kcalCount;

    @Column(name="train_count")
    private int trainCount;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Favorite> favorites = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<LatestTraining> latestTrainings = new ArrayList<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserCalendar userCalendar;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserAwards> userAwards;

    public User(String password,
                String email,
                String userName){
        this.password = password;
        this.email = email;
        this.userName = userName;
    }
}