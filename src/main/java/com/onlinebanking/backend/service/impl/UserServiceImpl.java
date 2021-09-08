package com.onlinebanking.backend.service.impl;

import com.onlinebanking.backend.persistent.domain.Role;
import com.onlinebanking.backend.persistent.domain.User;
import com.onlinebanking.backend.persistent.domain.UserHistory;
import com.onlinebanking.backend.persistent.domain.UserRole;
import com.onlinebanking.backend.persistent.repository.UserRepository;
import com.onlinebanking.backend.service.UserService;
import com.onlinebanking.backend.service.account.impl.AccountServiceImpl;
import com.onlinebanking.constant.CacheConstants;
import com.onlinebanking.constant.user.UserConstants;
import com.onlinebanking.enums.RoleType;
import com.onlinebanking.enums.UserHistoryType;
import com.onlinebanking.shared.dto.UserDto;
import com.onlinebanking.shared.util.StringUtils;
import com.onlinebanking.shared.util.UserUtils;
import com.onlinebanking.shared.util.validation.InputValidationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.core.userdetails.UserDetails;
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
    private final AccountServiceImpl accountServiceImpl;

    /**
     * Saves or updates the user with the user instance given.
     *
     * @param user the user with updated information
     * @param isUpdate if the operation is an update
     *
     * @return the updated user.
     * @throws IllegalArgumentException in case the given entity is {@literal null}
     */
    @Override
    @Transactional
    public UserDto saveOrUpdate(User user, boolean isUpdate) {
        User persistedUser = isUpdate ? userRepository.saveAndFlush(user) : userRepository.save(user);
        LOG.debug(UserConstants.USER_PERSISTED_SUCCESSFULLY, persistedUser);

        return UserUtils.convertToUserDto(persistedUser);
    }

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
            userDto.setPublicId(StringUtils.generatePublicId());
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            userDto.setCheckingAccount(accountServiceImpl.createCheckingAccount());
            userDto.setSavingsAccount(accountServiceImpl.createSavingsAccount());

            return persistUser(userDto, roleTypes, UserHistoryType.CREATED, false);
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
    @Cacheable(CacheConstants.USERS)
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
     * Returns a userDetails for the given username or null if a user could not be found.
     *
     * @param username The username associated to the user to find
     *
     * @return a user for the given username or null if a user could not be found
     * @throws IllegalArgumentException in case the given entity is {@literal null}
     */
    @Override
    public UserDetails getUserDetails(String username) {
        InputValidationUtils.validateInputs(getClass(), username);
        User storedUser = userRepository.findByUsername(username);

        return UserDetailsBuilder.buildUserDetails(storedUser);
    }

    /**
     * Checks if the username already exists and enabled.
     *
     * @param username the username
     *
     * @return <code>true</code> if username exists
     */
    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsernameOrderById(username);
    }

    /**
     * Update the user with the user instance given and the update type for record.
     *
     * @param userDto         The user with updated information
     * @param userHistoryType the history type to be recorded
     *
     * @return the updated user
     * @throws IllegalArgumentException in case the given entity is {@literal null}
     */
    @Override
    @Transactional
    @Caching(evict = {
            @CacheEvict(value = CacheConstants.USERS, key = "#userDto.username"),
            @CacheEvict(value = CacheConstants.USERS, key = "#userDto.publicId")
    })
    public UserDto updateUser(UserDto userDto, UserHistoryType userHistoryType) {
        InputValidationUtils.validateInputs(getClass(), userDto, userHistoryType);
        userDto.setVerificationToken(null);

        return persistUser(userDto, Collections.emptySet(), UserHistoryType.PROFILE_UPDATE, true);
    }

    /**
     * Transfers user details to a user object then persist to database.
     *
     * @param userDto         the userDto
     * @param roles       the roles
     * @param historyType the user history type
     * @param isUpdate if the operation is an update
     *
     * @return the userDto
     */
    private UserDto persistUser(UserDto userDto, Set<RoleType> roles, UserHistoryType historyType, boolean isUpdate) {
        var user = UserUtils.convertToUser(userDto);
        for (RoleType roleType : roles) {
            user.addUserRole(new UserRole(user, new Role(roleType)));
        }
        user.addUserHistory(new UserHistory(StringUtils.generatePublicId(), user, historyType));

        return saveOrUpdate(user, isUpdate);
    }
}
