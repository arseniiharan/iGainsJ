package com.iGainsTwo.iGainsJ.controllers;

import com.iGainsTwo.iGainsJ.DTO.exercise.ExerciseDTO;
import com.iGainsTwo.iGainsJ.exceptions.ExerciseNeverExistedException;
import com.iGainsTwo.iGainsJ.models.Exercise;
import com.iGainsTwo.iGainsJ.services.exercise.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercise")
@RequiredArgsConstructor
public class ExerciseController {
    private final ExerciseService exerciseService;

    @GetMapping("/getall")
    public ResponseEntity<?> getAllExercises() {
        try {
            List<Exercise> exercises = exerciseService.getAllExercises();
            return ResponseEntity.ok(exercises);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getexercise/{id}")
    public ResponseEntity<?> getExercise(@PathVariable Long id) {
        try {
            ExerciseDTO exerciseDTO = exerciseService.getExerciseById(id);
            return ResponseEntity.ok(exerciseDTO);
        } catch (ExerciseNeverExistedException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
