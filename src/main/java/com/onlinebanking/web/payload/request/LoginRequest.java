package com.onlinebanking.web.payload.request;

import com.onlinebanking.enums.ErrorMessage;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * This will define the payload that is acceptable for the parameters needed to login.
 *
 * @author Stephen Boakye
 * @version 1.0
 * @since 1.0
 */
@Data
public class LoginRequest {

    @NotBlank(message = ErrorMessage.BLANK_USERNAME)
    @Size(min = 3, max = 50, message = ErrorMessage.USERNAME_SIZE)
    private String username;

    @ToString.Exclude
    @NotBlank(message = ErrorMessage.BLANK_PASSWORD)
    @Size(min = 4, max = 15, message = ErrorMessage.PASSWORD_SIZE)
    private String password;

}
