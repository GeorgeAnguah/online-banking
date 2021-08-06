package com.onlinebanking.web.payload.response;

import com.onlinebanking.enums.OperationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * This class models the format of the login response produced.
 *
 * @author Eric Opoku
 * @version 1.0
 * @since 1.0
 */
@Data
@AllArgsConstructor
public class LogoutResponse {

    private String message;
    private OperationStatus status;
}
