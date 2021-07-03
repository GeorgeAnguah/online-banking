package com.onlinebanking.constant;

import com.onlinebanking.enums.ErrorMessage;

/**
 * This class holds all profile type constants used in the application.
 *
 * @author Matthew Puentes on 6/22/2021
 * @version 1.0
 * @since 1.0
 */
public final class ProfileTypeConstants {

    /**
     * Dev Profile Constant.
     */
    public static final String DEV = "dev";
    /**
     * Prod Profile Constant.
     */
    public static final String PROD = "prod";
    /**
     * Test Profile Constant.
     */
    public static final String TEST = "test";

    private ProfileTypeConstants() {
        throw new AssertionError(ErrorMessage.NOT_INSTANTIABLE.getErrorMsg());
    }
}
