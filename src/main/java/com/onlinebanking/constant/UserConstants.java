package com.onlinebanking.constant;

import com.onlinebanking.enums.ErrorMessage;

/**
 * User constant provides details about user.
 *
 * @author Eric Opoku
 * @version 1.0
 * @since 1.0
 */
public final class UserConstants {

    public static final String USER_CREATED_SUCCESSFULLY = "User successfully created {}";
    public static final String USER_ALREADY_EXIST = "Email {} already exist and nothing will be done";
    public static final String USER_EXIST_BUT_NOT_ENABLED = "Email {} exists but not enabled. Returning user {}";

    private UserConstants() {
        throw new AssertionError(ErrorMessage.NOT_INSTANTIABLE.getErrorMsg());
    }
}
