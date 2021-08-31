package com.onlinebanking.shared.util;

import com.onlinebanking.enums.ErrorMessage;

import java.security.SecureRandom;

/**
 * This utility class holds random generations used in the application.
 *
 * @author Eric Opoku
 * @version 1.0
 * @since 1.0
 */
public final class RandomUtils {

    public static final SecureRandom RANDOM = new SecureRandom();

    private RandomUtils() {
        throw new AssertionError(ErrorMessage.NOT_INSTANTIABLE.getErrorMsg());
    }
}
