package com.iGainsTwo.iGainsJ.mappers;

import com.iGainsTwo.iGainsJ.DTO.userawards.AwardDTO;
import com.iGainsTwo.iGainsJ.models.UserAwards;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserAwardsMapper {
    @Mapping(target = "awardTitle", source = "dto.awardTitle")
    @Mapping(target = "awardImage", source = "dto.awardImage")
    @Mapping(target = "awardDescription", source = "dto.awardDescription")
    UserAwards toModel(AwardDTO dto);

    @Mapping(target = "awardTitle", source = "userAwards.awardTitle")
    @Mapping(target = "awardImage", source = "userAwards.awardImage")
    @Mapping(target = "awardDescription", source = "userAwards.awardDescription")
    AwardDTO toDto(UserAwards userAwards);
}
