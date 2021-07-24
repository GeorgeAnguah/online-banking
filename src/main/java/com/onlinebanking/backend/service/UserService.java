package com.onlinebanking.backend.service;

import com.onlinebanking.enums.RoleType;
import com.onlinebanking.shared.dto.UserDto;

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
     * Create the userDto with the userDto instance given.
     *
     * @param userDto   the userDto with updated information
     * @param roleTypes the roleTypes.
     * @return the updated userDto.
     * @throws IllegalArgumentException in case the given entity is
     *                                  {@literal null}
     */
    UserDto createUser(final UserDto userDto, final Set<RoleType> roleTypes);
}
