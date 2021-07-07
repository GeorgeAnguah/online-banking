package com.onlinebanking.shared;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import com.onlinebanking.backend.persistent.domain.User;

import java.text.MessageFormat;
import java.util.Locale;

public final class UserUtils {
    private static final Faker FAKER = new Faker();
    private static final int MIN_LENGTH = 4;
    public static final int MAX_LENGTH = 15;

    private UserUtils() {
    }

    public static User createUser() {
        return createUser(FAKER.name().username());
    }

    /**
     * Create a user with some flexibility.
     *
     * @param username
     * @return
     */
    public static User createUser(String username) {
        return createUser(username, FAKER.internet().password(MIN_LENGTH, MAX_LENGTH), FAKER.internet().emailAddress());
    }

    public static User createUser(String username, String password, String email) {
        return createUser(username, password, email, false);
    }

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

    public static String generatePublicId() {
        return generatePublicId(30);
    }

    public static String generatePublicId(int length) {
        FakeValuesService fakeValuesService = new FakeValuesService(Locale.ENGLISH, new RandomService());
        return fakeValuesService.regexify(MessageFormat.format("[A-Z1-9a-z]'{'{0}'}'", length));
    }
}
