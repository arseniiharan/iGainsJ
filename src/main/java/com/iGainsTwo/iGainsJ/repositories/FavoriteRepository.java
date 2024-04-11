package com.iGainsTwo.iGainsJ.repositories;

import com.iGainsTwo.iGainsJ.models.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    Optional<Favorite> findByExerciseId(Long id);
}
