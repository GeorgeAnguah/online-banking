package com.onlinebanking.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Class ErrorConstants will hold all the error messages used in the application.
 *
 * @author George on 6/21/2021
 * @version 1.0
 * @since 1.0
 */

@Getter
@RequiredArgsConstructor
public enum ErrorConstants {

    MISSING_REQUIRED_FIELD("Missing required field. Please check the documentation for required fields"),
    RECORD_ALREADY_EXISTS("Record already exists"),
    INTERNAL_SERVER_ERROR("Internal Server error"),
    NO_RECORD_FOUND("Record with provided identifier is not found"),
    USER_NOT_FOUND("User not found"),
    AUTHENTICATION_FAILED("Authentication failed"),
    COULD_NOT_UPDATE_RECORD("Could not update record"),
    COULD_NOT_DELETE_RECORD("Could not delete record"),
    EMAIL_ADDRESS_NOT_VERIFIED("Email address could not be verified"),
    NOT_INSTANTIABLE("This class cannot be instantiated");

    private final String errorMsg;




}
