package com.onlinebanking.constant.user;

import com.onlinebanking.enums.ErrorMessage;

/**
 * User constant provides details about user.
 *
 * @author Eric Opoku
 * @version 1.0
 * @since 1.0
 */
public final class UserConstants {

    public static final String USER_MODEL_KEY = "user";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String USERNAME = "username";
    public static final String PUBLIC_ID = "publicId";

    public static final String USER_PERSISTED_SUCCESSFULLY = "User successfully persisted {}";
    public static final String USER_ALREADY_EXIST = "Email {} already exist and nothing will be done";
    public static final String USER_EXIST_BUT_NOT_ENABLED = "Email {} exists but not enabled. Returning user {}";

    private UserConstants() {
        throw new AssertionError(ErrorMessage.NOT_INSTANTIABLE.getErrorMsg());
    }
}
