package com.iGainsTwo.iGainsJ.controllers;

import com.iGainsTwo.iGainsJ.DTO.exercise.AddDelFavoriteExerciseDTO;
import com.iGainsTwo.iGainsJ.exceptions.AlreadyInFavoritesException;
import com.iGainsTwo.iGainsJ.exceptions.ExerciseNeverExistedException;
import com.iGainsTwo.iGainsJ.exceptions.FavoriteNeverExistedException;
import com.iGainsTwo.iGainsJ.exceptions.UserNeverExistedException;
import com.iGainsTwo.iGainsJ.services.favorites.FavoritesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/favorites")
@RequiredArgsConstructor
public class FavoritesController {
    private final FavoritesService favoritesService;

    @PostMapping("/add")
    public ResponseEntity<?> addFavorite(@RequestBody AddDelFavoriteExerciseDTO addDelFavoriteExerciseDTO) {
        try {
            favoritesService.addFavorite(addDelFavoriteExerciseDTO);
            return ResponseEntity.ok("Favorite for user saved");
        } catch (UserNeverExistedException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (ExerciseNeverExistedException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (AlreadyInFavoritesException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Bad request");
        }
    }

    @DeleteMapping("/del")
    public ResponseEntity<?> deleteFavorite(@RequestBody AddDelFavoriteExerciseDTO addDelFavoriteExerciseDTO) {
        try {
            favoritesService.deleteFavorite(addDelFavoriteExerciseDTO);
            return ResponseEntity.ok("Favorite successfully deleted");
        } catch (UserNeverExistedException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (FavoriteNeverExistedException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Bad request");
        }
    }
}
