package com.onlinebanking;

import com.google.gson.Gson;
import com.onlinebanking.constant.ProfileTypeConstants;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Assertions;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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
    public static final String ANONYMOUS_ROLE = "ROLE_ANONYMOUS";
    public static final String ANONYMOUS_USER = "anonymousUser";
    public static final String ROLE_USER = "ROLE_USER";

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
     * Sets the authentication object for unit testing purposes.
     *
     * @param role     the role to be assigned
     * @param username the user to authenticate
     */
    public static void setAuthentication(String role, String username) {
        var authorities = Collections.singletonList(new SimpleGrantedAuthority(role));
        Authentication auth;
        if (username.equals(ANONYMOUS_USER)) {
            var user = User.builder().username(username).password(username).authorities(authorities).build();
            auth = new AnonymousAuthenticationToken(username, user, authorities);
        } else {
            auth = new UsernamePasswordAuthenticationToken(username, null, authorities);
        }
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    /**
     * Converts an object to JSON string.
     *
     * @param object the object
     * @param <T>    the type of object passed
     *
     * @return the json string
     */
    public static <T> String toJson(T object) {
        return new Gson().toJson(object);
    }

    /**
     * Parse a JSON string to an object.
     *
     * @param content   the content to be parsed
     * @param classType the class to be returned
     * @param <T>       the class type
     *
     * @return the parsed object
     */
    public static <T> T parse(String content, Class<T> classType) {
        return new Gson().fromJson(content, classType);
    }

}
