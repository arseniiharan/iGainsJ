package com.iGainsTwo.iGainsJ.DTO.userawards;

import lombok.Data;

import java.util.ArrayList;
import java.util.UUID;

@Data
public class UserAwardsDTO {
    Long id;
    UUID userId;
    ArrayList<AwardDTO> userAwards;
}
