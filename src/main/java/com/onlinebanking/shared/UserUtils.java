package com.onlinebanking.shared;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import com.onlinebanking.backend.persistent.domain.User;

import java.text.MessageFormat;
import java.util.Locale;

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
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setPublicId(generatePublicId());
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
     * Generate a public id for user.
     *
     * @return public id
     */
    public static String generatePublicId() {
        return generatePublicId(30);
    }

    /**
     * Generate a public id for user.
     *
     * @param length length used for public id.
     * @return public id
     */
    public static String generatePublicId(int length) {
        FakeValuesService fakeValuesService = new FakeValuesService(Locale.ENGLISH, new RandomService());
        return fakeValuesService.regexify(MessageFormat.format("[A-Z1-9a-z]'{'{0}'}'", length));
    }
}
