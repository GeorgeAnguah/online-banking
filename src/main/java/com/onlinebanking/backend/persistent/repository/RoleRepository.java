package com.onlinebanking.backend.persistent.repository;

import com.onlinebanking.backend.persistent.domain.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for the role.
 *
 * @author George on 6/28/2021
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

    /**
     * Gets role associated with required name.
     *
     * @param name name of role.
     *
     * @return Role found.
     */
    Role findByName(String name);
}
