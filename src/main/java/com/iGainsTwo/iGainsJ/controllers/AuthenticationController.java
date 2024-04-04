package com.iGainsTwo.iGainsJ.controllers;

import com.iGainsTwo.iGainsJ.DTO.UserLoginRequestDTO;
import com.iGainsTwo.iGainsJ.DTO.UserLoginResponseDTO;
import com.iGainsTwo.iGainsJ.DTO.UserRegistrationRequestDTO;
import com.iGainsTwo.iGainsJ.DTO.UserResponseDTO;
import com.iGainsTwo.iGainsJ.exceptions.UserExistsException;
import com.iGainsTwo.iGainsJ.security.AuthService;
import com.iGainsTwo.iGainsJ.services.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {
    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody @Valid UserRegistrationRequestDTO userRegistrationRequestDTO) {
        try {
            userService.userRegistration(userRegistrationRequestDTO);
            UserResponseDTO user = userService.userFindByEmail(userRegistrationRequestDTO.getEmail());
            return ResponseEntity.ok("User saved successfully: ");
        } catch (UserExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Bad registration request");
        }
    }

    @PostMapping("/signin")
    public UserLoginResponseDTO login(@RequestBody @Valid UserLoginRequestDTO userLoginRequestDTO) {
        return authService.authenticate(userLoginRequestDTO);
    }
}
