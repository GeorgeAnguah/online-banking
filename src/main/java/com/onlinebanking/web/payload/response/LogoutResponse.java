package com.onlinebanking.web.payload.response;

import com.onlinebanking.enums.OperationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * This class models the format of the login response produced.
 *
 * @author Eric Opoku
 * @version 1.0
 * @since 1.0
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class LogoutResponse implements Serializable {
    private static final long serialVersionUID = -133371944006730313L;

    private String message = "Logout successful. Tokens are removed from cookie.";

    @NonNull
    private OperationStatus status;
}
