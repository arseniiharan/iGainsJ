package com.iGainsTwo.iGainsJ.controllers;

import com.iGainsTwo.iGainsJ.DTO.UserRegistrationRequestDTO;
import com.iGainsTwo.iGainsJ.DTO.UserResponseDTO;
import com.iGainsTwo.iGainsJ.exceptions.UserExistsException;
import com.iGainsTwo.iGainsJ.exceptions.UserNeverExistedException;
import com.iGainsTwo.iGainsJ.services.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/reg")
    public ResponseEntity postUser(@RequestBody @Valid UserRegistrationRequestDTO userRegistrationRequestDTO) {
        try {
            userService.userRegistration(userRegistrationRequestDTO);
            UserResponseDTO user = userService.userFindByEmail(userRegistrationRequestDTO.getEmail());
            return ResponseEntity.ok("User saved successfully: " + user.toString());
        } catch (UserExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Bad registration request");
        }
    }

    @GetMapping
    public ResponseEntity getUser(@RequestParam String email) {
        try {
            UserResponseDTO user = userService.userFindByEmail(email);
            return ResponseEntity.ok(user);
        } catch (UserNeverExistedException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Bad get request");
        }
    }

    @DeleteMapping("/del/{email}")
    public ResponseEntity deleteUser(@PathVariable String email) {
        try {
            userService.userDeleteByEmail(email);
            return ResponseEntity.ok("User deleted successfully");
        } catch (UserNeverExistedException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Bad delete request");
        }
    }

}
