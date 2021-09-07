package com.onlinebanking.constant;

import com.onlinebanking.enums.ErrorMessage;

/**
 * This class holds all account constants used in the application.
 *
 * @author Matthew Puentes
 * @version 1.0
 * @since 1.0
 */
public final class AccountConstants {

    public static final String ACCOUNT_URL_MAPPING = "/account";
    public static final String CHECKING_ACCOUNT_OVERVIEW_MODEL_ATTRIBUTE = "checkingAccount";
    public static final String SAVINGS_ACCOUNT_OVERVIEW_MODEL_ATTRIBUTE = "savingsAccount";
    public static final String CHECKING_ACCOUNT = "CheckingAccount";
    public static final String SAVINGS_ACCOUNT = "SavingsAccount";

    private AccountConstants() {
        throw new AssertionError(ErrorMessage.NOT_INSTANTIABLE.getErrorMsg());
    }
}
