package com.onlinebanking.shared.util;

import com.github.javafaker.Faker;
import com.onlinebanking.backend.persistent.domain.User;
import com.onlinebanking.enums.ErrorMessage;
import com.onlinebanking.shared.dto.UserDto;
import com.onlinebanking.shared.dto.mapper.UserDtoMapper;
import com.onlinebanking.shared.util.validation.InputValidationUtils;

/**
 * User utility class that holds methods used across application.
 *
 * @author Matthew Puentes
 * @version 1.0
 * @since 1.0
 */
public final class UserUtils {

    private static final Faker FAKER = new Faker();
    private static final int MIN_LENGTH = 4;
    public static final int MAX_LENGTH = 15;

    private UserUtils() {
        throw new AssertionError(ErrorMessage.NOT_INSTANTIABLE.getErrorMsg());
    }

    /**
     * Create a user.
     *
     * @return a user
     */
    public static User createUser() {
        return createUser(FAKER.name().username());
    }

    /**
     * Create a user with some flexibility.
     *
     * @param username username used to create user.
     * @return a user
     */
    public static User createUser(String username) {
        return createUser(username, FAKER.internet().password(MIN_LENGTH, MAX_LENGTH), FAKER.internet().emailAddress());
    }

    /**
     * Create a user with some flexibility.
     *
     * @param username username used to create user
     * @param password password used to create user.
     * @param email email used to create user.
     * @return a user
     */
    public static User createUser(String username, String password, String email) {
        return createUser(username, password, email, false);
    }

    /**
     * Create user with some flexibility.
     *
     * @param username username used to create user.
     * @param password password used to create user.
     * @param email email used to create user.
     * @param enabled boolean value used to evaluate if user enabled.
     * @return a user
     */
    public static User createUser(String username, String password, String email, boolean enabled) {
        var user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setPublicId(StringUtils.generatePublicId());
        user.setEmail(email);
        user.setPhone(FAKER.phoneNumber().cellPhone());
        user.setFirstName(FAKER.name().firstName());
        user.setLastName(FAKER.name().lastName());

        if (enabled) {
            user.setEnabled(true);
        }
        return user;
    }

    /**
     * Create a test user with flexibility.
     *
     * @param username the username
     *
     * @return the userEntity
     */
    public static UserDto createUserDto(final String username) {
        return UserUtils.convertToUserDto(createUser(username));
    }

    /**
     * Transfers data from entity to transfer object.
     *
     * @param user stored user details
     * @return user dto
     */
    public static UserDto convertToUserDto(final User user) {
        var userDto = UserDtoMapper.MAPPER.toUserDto(user);
        InputValidationUtils.validateInputs(userDto);
        return userDto;

    }

    /**
     * Transfers data from transfer object to entity.
     *
     * @param userDto the userDto
     * @return user
     */
    public static User convertToUser(final UserDto userDto) {
        var user = UserDtoMapper.MAPPER.toUser(userDto);
        InputValidationUtils.validateInputs(user);
        return user;
    }

    /**
     * Enables and unlocks a user.
     *
     * @param userDto the userDto
     */
    public static void enableUser(final UserDto userDto) {
        InputValidationUtils.validateInputs(userDto);
        userDto.setEnabled(true);
    }
}
