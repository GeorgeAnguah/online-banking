package com.onlinebanking.backend.service;

import com.onlinebanking.backend.persistent.domain.User;
import com.onlinebanking.enums.RoleType;
import com.onlinebanking.enums.UserHistoryType;
import com.onlinebanking.shared.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

/**
 * This UserService interface is the contract for the user service operations.
 *
 * @author Matthew Puentes on 7/15/2021
 * @version 1.0
 * @since 1.0
 */
public interface UserService {

    /**
     * Saves or updates the user with the user instance given.
     *
     * @param user the user with updated information
     * @param isUpdate if the operation is an update
     *
     * @return the updated user.
     * @throws IllegalArgumentException in case the given entity is {@literal null}
     */
    UserDto saveOrUpdate(User user, boolean isUpdate);

    /**
     * Create the userDto with the userDto instance given.
     *
     * @param userDto the userDto with updated information
     *
     * @return the updated userDto.
     * @throws IllegalArgumentException in case the given entity is {@literal null}
     */
    UserDto createUser(final UserDto userDto);

    /**
     * Create the userDto with the userDto instance given.
     *
     * @param userDto   the userDto with updated information
     * @param roleTypes the roleTypes.
     *
     * @return the updated userDto.
     * @throws IllegalArgumentException in case the given entity is {@literal null}
     */
    UserDto createUser(final UserDto userDto, final Set<RoleType> roleTypes);

    /**
     * Returns a user for the given username or null if a user could not be found.
     *
     * @param username The username associated to the user to find
     *
     * @return a user for the given username or null if a user could not be found
     * @throws IllegalArgumentException in case the given entity is {@literal null}
     */
    UserDto findByUsername(String username);

    /**
     * Returns a user for the given email or null if a user could not be found.
     *
     * @param email The email associated to the user to find
     *
     * @return a user for the given email or null if a user could not be found
     * @throws IllegalArgumentException in case the given entity is {@literal null}
     */
    UserDto findByEmail(String email);

    /**
     * Returns a userDetails for the given username or null if a user could not be found.
     *
     * @param username The username associated to the user to find
     *
     * @return a user for the given username or null if a user could not be found
     * @throws IllegalArgumentException in case the given entity is {@literal null}
     */
    UserDetails getUserDetails(String username);

    /**
     * Checks if the username already exists and enabled.
     *
     * @param username the username
     *
     * @return <code>true</code> if username exists
     */
    boolean existsByUsername(String username);

    /**
     * Update the user with the user instance given and the update type for record.
     *
     * @param userDto         The user with updated information
     * @param userHistoryType the history type to be recorded
     *
     * @return the updated user
     * @throws IllegalArgumentException in case the given entity is {@literal null}
     */
    UserDto updateUser(UserDto userDto, UserHistoryType userHistoryType);
}
