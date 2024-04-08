package com.iGainsTwo.iGainsJ.services.userawards.impl;

import com.iGainsTwo.iGainsJ.DTO.userawards.AwardDTO;
import com.iGainsTwo.iGainsJ.DTO.userawards.GiveAwardDTO;
import com.iGainsTwo.iGainsJ.exceptions.AwardNeverExistedException;
import com.iGainsTwo.iGainsJ.exceptions.UserNeverExistedException;
import com.iGainsTwo.iGainsJ.mappers.UserAwardsMapper;
import com.iGainsTwo.iGainsJ.models.User;
import com.iGainsTwo.iGainsJ.models.UserAwards;
import com.iGainsTwo.iGainsJ.repositories.UserAwardsRepository;
import com.iGainsTwo.iGainsJ.repositories.UserRepository;
import com.iGainsTwo.iGainsJ.services.userawards.UserAwardsService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAwardsServiceImpl implements UserAwardsService {
    private final UserAwardsRepository userAwardsRepository;
    private final UserRepository userRepository;

    private final UserAwardsMapper userAwardsMapper;

    @Override
    @Transactional
    public AwardDTO giveUserAward(GiveAwardDTO giveAwardDTO) throws UserNeverExistedException, AwardNeverExistedException {
        Optional<User> userOptional = userRepository.findById(giveAwardDTO.userId());
        if (userOptional.isEmpty()) {
            throw new UserNeverExistedException("User with that email doesn't exist");
        }
        Optional<UserAwards> awardOptional = userAwardsRepository.findById(giveAwardDTO.awardId());
        if (awardOptional.isEmpty()) {
            throw new AwardNeverExistedException("This award doesn't exist");
        }
        UserAwards award = awardOptional.get();
        User user = userOptional.get();
        user.getUserAwards().add(award);
        userRepository.save(user);
        return userAwardsMapper.toDto(award);
    }
}
