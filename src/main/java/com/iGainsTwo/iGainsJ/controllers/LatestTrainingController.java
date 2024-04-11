package com.iGainsTwo.iGainsJ.controllers;

import com.iGainsTwo.iGainsJ.DTO.exercise.AddLastTrainingDTO;
import com.iGainsTwo.iGainsJ.exceptions.ExerciseNeverExistedException;
import com.iGainsTwo.iGainsJ.exceptions.UserNeverExistedException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.iGainsTwo.iGainsJ.services.latesttraining.LatestTrainingService;

@RestController
@RequestMapping("/training")
@RequiredArgsConstructor
public class LatestTrainingController {
    private final LatestTrainingService latestTrainingService;

    @PostMapping("/add")
    public ResponseEntity<?> giveUserAward(@RequestBody AddLastTrainingDTO addLastTrainingDTO) {
        try {
            latestTrainingService.addLastTraining(addLastTrainingDTO);
            return ResponseEntity.ok("Exercise for user saved");
        } catch (UserNeverExistedException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (ExerciseNeverExistedException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Bad request");
        }
    }
}
