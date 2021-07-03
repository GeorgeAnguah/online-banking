package com.onlinebanking.constant;

import com.onlinebanking.enums.ErrorMessage;

/**
 * This class holds all Home constants used in the application.
 *
 * @author Matthew Puentes
 * @version 1.0
 * @since 1.0
 */

public final class HomeConstants {

    /**
     * URL Mapping Constants.
     */
    public static final String INDEX_URL_MAPPING = "/";

    /**
     * View Name Constants.
     */
    public static final String INDEX_VIEW_NAME = "index";

    private HomeConstants() {
        throw new AssertionError(ErrorMessage.NOT_INSTANTIABLE.getErrorMsg());
    }
}
