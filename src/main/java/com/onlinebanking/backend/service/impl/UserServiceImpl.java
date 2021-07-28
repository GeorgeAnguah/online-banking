package com.onlinebanking.backend.service.impl;

import com.onlinebanking.backend.persistent.domain.Role;
import com.onlinebanking.backend.persistent.domain.User;
import com.onlinebanking.backend.persistent.domain.UserHistory;
import com.onlinebanking.backend.persistent.domain.UserRole;
import com.onlinebanking.backend.persistent.repository.UserRepository;
import com.onlinebanking.backend.service.UserService;
import com.onlinebanking.constant.UserConstants;
import com.onlinebanking.enums.RoleType;
import com.onlinebanking.enums.UserHistoryType;
import com.onlinebanking.shared.dto.UserDto;
import com.onlinebanking.shared.util.StringUtils;
import com.onlinebanking.shared.util.UserUtils;
import com.onlinebanking.shared.util.validation.InputValidationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

/**
 * The UserServiceImpl class provides implementation for the UserService definitions.
 *
 * @author Eric Opoku
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Create the userDto with the userDto instance given.
     *
     * @param userDto the userDto with updated information
     *
     * @return the updated userDto.
     * @throws IllegalArgumentException in case the given entity is {@literal null}
     */
    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {
        InputValidationUtils.validateInputs(getClass(), userDto);
        return createUser(userDto, Collections.singleton(RoleType.ROLE_CUSTOMER));
    }

    /**
     * Create the userDto with the userDto instance given.
     *
     * @param userDto   the userDto with updated information
     * @param roleTypes the roleTypes.
     *
     * @return the updated userDto.
     * @throws IllegalArgumentException in case the given entity is {@literal null}
     */
    @Override
    @Transactional
    public UserDto createUser(UserDto userDto, Set<RoleType> roleTypes) {
        InputValidationUtils.validateInputs(getClass(), userDto, roleTypes);

        User localUser = userRepository.findByEmail(userDto.getEmail());
        if (Objects.nonNull(localUser)) {
            if (!localUser.isEnabled()) {
                LOG.debug(UserConstants.USER_EXIST_BUT_NOT_ENABLED, userDto.getEmail(), localUser);
                return UserUtils.convertToUserDto(localUser);
            }
            LOG.warn(UserConstants.USER_ALREADY_EXIST, userDto.getEmail());
        } else {
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            userDto.setPublicId(StringUtils.generatePublicId());
            return persistUser(userDto, roleTypes, UserHistoryType.CREATED);
        }
        return null;
    }

    /**
     * Returns a user for the given username or null if a user could not be found.
     *
     * @param username The username associated to the user to find
     *
     * @return a user for the given username or null if a user could not be found
     * @throws IllegalArgumentException in case the given entity is {@literal null}
     */
    @Override
    public UserDto findByUsername(String username) {
        InputValidationUtils.validateInputs(this, username);
        User storedUser = userRepository.findByUsername(username);
        if (Objects.isNull(storedUser)) {
            return null;
        }
        return UserUtils.convertToUserDto(storedUser);
    }

    /**
     * Returns a user for the given email or null if a user could not be found.
     *
     * @param email The email associated to the user to find
     *
     * @return a user for the given email or null if a user could not be found
     * @throws IllegalArgumentException in case the given entity is {@literal null}
     */
    @Override
    public UserDto findByEmail(String email) {
        InputValidationUtils.validateInputs(this, email);
        User storedUser = userRepository.findByEmail(email);
        if (Objects.isNull(storedUser)) {
            return null;
        }
        return UserUtils.convertToUserDto(storedUser);
    }

    /**
     * Transfers user details to a user object then persist to database.
     *
     * @param userDto         the userDto
     * @param roleTypes       the roleTypes
     * @param userHistoryType the user history type
     *
     * @return the userDto
     */
    private UserDto persistUser(UserDto userDto, Set<RoleType> roleTypes, UserHistoryType userHistoryType) {
        var user = UserUtils.convertToUser(userDto);
        for (RoleType roleType : roleTypes) {
            user.addUserRole(new UserRole(user, new Role(roleType)));
        }
        user.addUserHistory(new UserHistory(StringUtils.generatePublicId(), user, userHistoryType));

        var persistedUser = userRepository.save(user);
        LOG.debug(UserConstants.USER_CREATED_SUCCESSFULLY, persistedUser);
        return UserUtils.convertToUserDto(persistedUser);
    }
}
