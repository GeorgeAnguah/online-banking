package com.onlinebanking.constant;

import com.onlinebanking.enums.ErrorMessage;

/**
 * Generic system constants used in the application.
 *
 * @author Eric Opoku
 * @version 1.0
 * @since 1.0
 */
public final class SystemConstants {

    public static final String ERROR = "error";
    public static final String ERROR_MESSAGE = "errorMessage";
    public static final String MAIN_INTAKE_SITE_NAME = "Online Banking";

    private SystemConstants() {
        throw new AssertionError(ErrorMessage.NOT_INSTANTIABLE.getErrorMsg());
    }
}
