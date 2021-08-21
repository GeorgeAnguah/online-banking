package com.onlinebanking.shared.util;

import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import com.onlinebanking.enums.ErrorMessage;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.UUID;

/**
 * This utility class holds custom operations on strings used in the application.
 *
 * @author Eric Opoku
 * @version 1.0
 * @since 1.0
 */
public final class StringUtils {

    /**
     * Generate an alpha-numeric with specified length.
     *
     * @param length the length of characters
     * @return the generated string
     */
    public static String generateAlphaNumericId(final int length) {
        var fakeValuesService = new FakeValuesService(Locale.ENGLISH, new RandomService());
        return fakeValuesService.regexify(MessageFormat.format("[A-Z1-9a-z]'{'{0}'}'", length));
    }

    /**
     * Generates a default character length alpha-numeric.
     *
     * @return the publicId
     */
    public static String generatePublicId() {
        return UUID.randomUUID().toString();
    }

    private StringUtils() {
        throw new AssertionError(ErrorMessage.NOT_INSTANTIABLE.getErrorMsg());
    }
}
