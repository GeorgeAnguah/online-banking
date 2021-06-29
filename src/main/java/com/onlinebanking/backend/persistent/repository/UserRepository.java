package com.onlinebanking.backend.persistent.repository;

import com.onlinebanking.backend.persistent.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for the User.
 *
 * @author Matthew Puentes on 6/29/2021
 * @version 1.0
 * @since 1.0
 */

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * Find user by email.
     *
     * @param email email used to search for user.
     *
     * @return User found.
     */
    User findByEmail(String email);

    /**
     * Check if user exists by email.
     *
     * @param email email to check if user exists.
     *
     * @return True if user exists or false otherwise.
     */
    Boolean existsByEmailOrderById(String email);

    /**
     * Find user by username.
     *
     * @param username username used to search for user.
     *
     * @return User found.
     */
    User findByUsername(String username);

    /**
     * Check if user exists by username.
     *
     * @param username username to check if user exists.
     *
     * @return True if user exists or false otherwise.
     */
    Boolean existsByUsernameOrderById(String username);

    /**
     * Find user by public id.
     *
     * @param publicId publicId used to search for user.
     *
     * @return User found.
     */
    User findByPublicId(String publicId);
}
