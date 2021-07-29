package com.onlinebanking;

import com.onlinebanking.backend.persistent.domain.User;
import com.onlinebanking.constant.ProfileTypeConstants;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Assertions;
import org.springframework.test.context.ActiveProfiles;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * This class holds common test functionalities to be used with other Test.
 *
 * @author George on 6/25/2021
 * @version 1.0
 * @since 1.0
 */
@ActiveProfiles(ProfileTypeConstants.TEST)
public class TestUtils {
    private static final String[] IGNORED_FIELDS = {"id", "createdAt", "createdBy", "updatedAt", "updatedBy"};
    public static final String TEST_EMAIL_SUFFIX = "@email.com";

    public static Collection<String> getIgnoredFields() {
        return Collections.unmodifiableCollection(Arrays.asList(IGNORED_FIELDS));
    }

    /**
     * Asserts that the given class on invocation will throw the specified throwable.
     *
     * @param clazz     the class
     * @param throwable the class
     */
    public static void assertExceptionCause(Class<?> clazz, Class<? extends Throwable> throwable) {
        assertExceptionCause(clazz, throwable, "");
    }

    /**
     * Asserts that the given class on invocation will throw the specified throwable with the provided message.
     *
     * @param clazz     the class
     * @param throwable the class
     * @param message   the message
     */
    public static void assertExceptionCause(Class<?> clazz, Class<? extends Throwable> throwable, String message) {
        var constructors = clazz.getDeclaredConstructors();
        Constructor<?> privateConstructor = constructors[0];
        privateConstructor.setAccessible(true);

        var rootCause = AssertionsForClassTypes
                .assertThatExceptionOfType(InvocationTargetException.class)
                .isThrownBy(() -> {
                    var inputValidationUtils = clazz.cast(privateConstructor.newInstance());
                    Assertions.fail(inputValidationUtils.toString());
                })
                .withRootCauseInstanceOf(throwable);
        if (StringUtils.isNotBlank(message)) {
            rootCause.withStackTraceContaining(message);
        }
    }

    /**
     * Create user for testing.
     *
     * @param username required username.
     * @return created user.
     */
    public static User createUser(String username) {
        User user = new User();
        user.setUsername(username);
        user.setPublicId(username + "publicId");
        user.setEmail(username + TestUtils.TEST_EMAIL_SUFFIX);
        user.setPassword(username);
        return user;
    }

}
