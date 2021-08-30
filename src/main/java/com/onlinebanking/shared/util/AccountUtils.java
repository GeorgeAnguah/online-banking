package com.onlinebanking.shared.util;

/**
 * This utility class holds custom operations for account types used in the application.
 *
 * @author Matthew Puentes
 * @version 1.0
 * @since 1.0
 */
public class AccountUtils {
    private static int nextAccountNumber = 11223145;

    public static int accountNumberGenerator() {
        return ++nextAccountNumber;
    }
}
