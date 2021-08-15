package com.onlinebanking.web.payload.request;

import com.onlinebanking.enums.ErrorMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * This class models the format of the login request accepted.
 *
 * @author Stephen Boakye
 * @version 1.0
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @NotBlank(message = ErrorMessage.BLANK_USERNAME)
    @Size(min = 3, max = 50, message = ErrorMessage.USERNAME_SIZE)
    private String username;

    @NotBlank(message = ErrorMessage.BLANK_PASSWORD)
    @Size(min = 4, message = ErrorMessage.PASSWORD_SIZE)
    private String password;
}
