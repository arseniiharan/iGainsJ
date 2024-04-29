package com.iGainsTwo.iGainsJ.controllers;

import com.iGainsTwo.iGainsJ.DTO.user.UserParametersChangeDTO;
import com.iGainsTwo.iGainsJ.DTO.user.UserResponseDTO;
import com.iGainsTwo.iGainsJ.exceptions.UserNeverExistedException;
import com.iGainsTwo.iGainsJ.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/get/{email}")
    public ResponseEntity<?> getUser(@PathVariable String email) {
        try {
            UserResponseDTO user = userService.userFindByEmail(email);
            return ResponseEntity.ok(user);
        } catch (UserNeverExistedException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Bad get request");
        }
    }

    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
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

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PutMapping("/change")
    public ResponseEntity<?> changeUserParameters(@RequestBody UserParametersChangeDTO userParametersChangeDTO) {
        try {
            userService.changeUserParameters(userParametersChangeDTO);
            return ResponseEntity.ok("User info changed successfully");
        } catch (UserNeverExistedException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Bad change request");
        }
    }
}
