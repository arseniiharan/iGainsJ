package com.iGainsTwo.iGainsJ.repositories;

import com.iGainsTwo.iGainsJ.models.UserAwards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAwardsRepository extends JpaRepository<UserAwards, Long> {
}
