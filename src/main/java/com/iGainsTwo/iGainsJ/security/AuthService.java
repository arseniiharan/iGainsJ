package com.iGainsTwo.iGainsJ.security;

import com.iGainsTwo.iGainsJ.DTO.user.UserLoginRequestDTO;
import com.iGainsTwo.iGainsJ.DTO.user.UserLoginResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public UserLoginResponseDTO authenticate(UserLoginRequestDTO userLoginRequestDTO) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginRequestDTO.getEmail(), userLoginRequestDTO.getPassword())
        );
        String token = jwtUtil.generateToken(authentication.getName());
        return new UserLoginResponseDTO(token);
    }
}
