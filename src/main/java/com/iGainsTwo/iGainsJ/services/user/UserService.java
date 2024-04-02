package com.iGainsTwo.iGainsJ.services.user;

import com.iGainsTwo.iGainsJ.DTO.UserRegistrationRequestDTO;
import com.iGainsTwo.iGainsJ.DTO.UserResponseDTO;
import com.iGainsTwo.iGainsJ.exceptions.UserExistsException;
import com.iGainsTwo.iGainsJ.exceptions.UserNeverExistedException;

public interface UserService {
    UserResponseDTO userRegistration(UserRegistrationRequestDTO regRequestDTO) throws UserExistsException;
    UserResponseDTO userFindByEmail(String email) throws UserNeverExistedException;
    void userDeleteByEmail(String email) throws UserNeverExistedException;
}
