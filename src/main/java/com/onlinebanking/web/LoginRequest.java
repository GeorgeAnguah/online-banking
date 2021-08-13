package com.onlinebanking.web;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * DELETE BEFORE PUSHING TO REMOTE (TESTING PURPOSES)
 */
@Getter
@Setter
public class LoginRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
