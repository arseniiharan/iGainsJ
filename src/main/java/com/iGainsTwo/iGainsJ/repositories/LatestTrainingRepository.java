package com.iGainsTwo.iGainsJ.repositories;

import com.iGainsTwo.iGainsJ.models.LatestTraining;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LatestTrainingRepository extends JpaRepository<LatestTraining, Long> {
}
