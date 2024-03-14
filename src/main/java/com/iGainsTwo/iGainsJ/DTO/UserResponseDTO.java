package com.iGainsTwo.iGainsJ.DTO;

import com.iGainsTwo.iGainsJ.models.Favorite;
import com.iGainsTwo.iGainsJ.models.LatestTraining;
import com.iGainsTwo.iGainsJ.models.UserAwards;
import com.iGainsTwo.iGainsJ.models.UserCalendar;
import lombok.Data;

import java.util.List;

@Data
public class UserResponseDTO {
    private String email;
    private String userName;
    private int age;
    private int gender;
    private int weight;
    private int height;
    private String photo;
    private int kcalCount;
    private int trainCount;
    private List<LatestTraining> latestTrainings;
    private UserCalendar userCalendar;
    private List<Favorite> favorites;
    private List<UserAwards> userAwards;
}
