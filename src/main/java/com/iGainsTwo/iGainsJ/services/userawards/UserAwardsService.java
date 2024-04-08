package com.iGainsTwo.iGainsJ.services.userawards;

import com.iGainsTwo.iGainsJ.DTO.userawards.AwardDTO;
import com.iGainsTwo.iGainsJ.DTO.userawards.GiveAwardDTO;
import com.iGainsTwo.iGainsJ.exceptions.AwardNeverExistedException;
import com.iGainsTwo.iGainsJ.exceptions.UserNeverExistedException;

public interface UserAwardsService {
    AwardDTO giveUserAward(GiveAwardDTO giveAwardDTO) throws UserNeverExistedException, AwardNeverExistedException;
}
