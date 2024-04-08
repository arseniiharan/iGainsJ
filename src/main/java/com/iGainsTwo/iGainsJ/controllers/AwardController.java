package com.iGainsTwo.iGainsJ.controllers;

import com.iGainsTwo.iGainsJ.DTO.userawards.GiveAwardDTO;
import com.iGainsTwo.iGainsJ.exceptions.AwardNeverExistedException;
import com.iGainsTwo.iGainsJ.exceptions.UserNeverExistedException;
import com.iGainsTwo.iGainsJ.services.userawards.UserAwardsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/awards")
@RequiredArgsConstructor
public class AwardController {
    private final UserAwardsService userAwardsService;

    @PostMapping("/give")
    public ResponseEntity<?> giveUserAward(@RequestBody GiveAwardDTO giveAwardDTO){
        try {
            userAwardsService.giveUserAward(giveAwardDTO);
            return ResponseEntity.ok("Award for user saved");
        } catch (UserNeverExistedException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (AwardNeverExistedException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Bad request");
        }
    }
}
