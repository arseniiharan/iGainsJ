package com.iGainsTwo.iGainsJ.repositories;

import com.iGainsTwo.iGainsJ.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(Role.RoleName roleName);
}
