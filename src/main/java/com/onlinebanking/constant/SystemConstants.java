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
    public static final String SYSTEM_NAME = "Online Banking";
    public static final String ROUTING_NUMBER = "695984269";

    private SystemConstants() {
        throw new AssertionError(ErrorMessage.NOT_INSTANTIABLE.getErrorMsg());
    }
}
