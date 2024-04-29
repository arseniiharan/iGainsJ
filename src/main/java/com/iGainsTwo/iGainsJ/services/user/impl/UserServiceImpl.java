package com.iGainsTwo.iGainsJ.services.user.impl;

import com.iGainsTwo.iGainsJ.DTO.user.UserParametersChangeDTO;
import com.iGainsTwo.iGainsJ.DTO.user.UserRegistrationRequestDTO;
import com.iGainsTwo.iGainsJ.DTO.user.UserResponseDTO;
import com.iGainsTwo.iGainsJ.exceptions.UserExistsException;
import com.iGainsTwo.iGainsJ.exceptions.UserNeverExistedException;
import com.iGainsTwo.iGainsJ.mappers.UserMapper;
import com.iGainsTwo.iGainsJ.models.Role;
import com.iGainsTwo.iGainsJ.models.User;
import com.iGainsTwo.iGainsJ.models.UserCalendar;
import com.iGainsTwo.iGainsJ.repositories.RoleRepository;
import com.iGainsTwo.iGainsJ.repositories.UserCalendarRepository;
import com.iGainsTwo.iGainsJ.repositories.UserRepository;
import com.iGainsTwo.iGainsJ.services.user.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserCalendarRepository userCalendarRepository;
    private final RoleRepository roleRepository;

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

   @Override
   @Transactional
    public UserResponseDTO userRegistration(UserRegistrationRequestDTO regRequestDTO) throws UserExistsException {
        if (userRepository.findByEmail(regRequestDTO.getEmail()).isPresent()) {
            throw new UserExistsException("User with t" +
                    "his email already exists");
        }
        regRequestDTO.setPassword(passwordEncoder.encode(regRequestDTO.getPassword()));
        User user = userMapper.toModel(regRequestDTO);
        Role role = roleRepository.findByRoleName(Role.RoleName.ROLE_USER);
        user.setRoles(Set.of(role));
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    @Transactional
    public UserResponseDTO userFindByEmail(String email) throws UserNeverExistedException {
       Optional<User> userOptional = userRepository.findByEmail(email);
       if (userOptional.isEmpty()) {
           throw new UserNeverExistedException("User with that email doesn't exist");
       }
       User user = userOptional.get();
       return userMapper.toDto(user);
    }

    @Override
    @Transactional
    public void userDeleteByEmail(String email) throws UserNeverExistedException {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new UserNeverExistedException("User with that email doesn't exist");
        }
        User user = userOptional.get();
        userRepository.delete(user);
    }

    @Override
    @Transactional
    public void changeUserParameters(UserParametersChangeDTO userParametersChangeDTO) throws UserNeverExistedException {
        Optional<User> userOptional = userRepository.findByEmail(userParametersChangeDTO.email());
        User user = userOptional.orElseThrow(() -> new UserNeverExistedException("This user doesn't exist"));

        user.setHeight(userParametersChangeDTO.height());
        user.setWeight(userParametersChangeDTO.weight());
        user.setAge(userParametersChangeDTO.age());
        userRepository.save(user);
    }
}
