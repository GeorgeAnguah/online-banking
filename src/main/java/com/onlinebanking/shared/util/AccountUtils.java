package com.onlinebanking.shared.util;

import com.onlinebanking.enums.ErrorMessage;

/**
 * This utility class holds custom operations for account types used in the application.
 *
 * @author Matthew Puentes
 * @version 1.0
 * @since 1.0
 */
public final class AccountUtils {
    private static long nextAccountNumber = 69_119_573;

    private AccountUtils() {
        throw new AssertionError(ErrorMessage.NOT_INSTANTIABLE.getErrorMsg());
    }

    /**
     * Account number generator.
     *
     * @return account number.
     */
    public static long accountNumberGenerator() {
        return ++nextAccountNumber;
    }
}
