package com.iGainsTwo.iGainsJ.mappers;

import com.iGainsTwo.iGainsJ.DTO.user.UserRegistrationRequestDTO;
import com.iGainsTwo.iGainsJ.DTO.user.UserResponseDTO;
import com.iGainsTwo.iGainsJ.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "email", source = "dto.email")
    @Mapping(target = "userName", source = "dto.userName")
    @Mapping(target = "password", source = "dto.password")
    @Mapping(target = "age", source = "dto.age")
    @Mapping(target = "height", source = "dto.height")
    @Mapping(target = "weight", source = "dto.weight")
    @Mapping(target = "gender", source = "dto.gender")
    User toModel(UserRegistrationRequestDTO dto);

    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "userName", source = "user.username")
    @Mapping(target = "age", source = "user.age")
    @Mapping(target = "height", source = "user.height")
    @Mapping(target = "weight", source = "user.weight")
    @Mapping(target = "gender", source = "user.gender")
    UserResponseDTO toDto(User user);
}
