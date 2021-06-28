package com.onlinebanking.backend.persistent.repository;

import com.onlinebanking.backend.persistent.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for the role.
 *
 * @author George on 6/28/2021
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    /**
     * Gets role associated with name.
     * @param name name of role.
     * @return Role found.
     */
    Role findByName(String name);
}
