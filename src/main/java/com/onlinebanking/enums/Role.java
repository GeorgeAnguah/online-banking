package com.onlinebanking.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Role defines the role to be assigned to each user of the application.
 * Roles : CUSTOMER , ADMIN.
 *
 * @author George on 6/24/2021
 * @version 1.0
 * @since 1.0
 */
@Getter
@RequiredArgsConstructor
public enum Role {
    CUSTOMER("ROLE_CUSTOMER"),
    ADMIN("ROLE_ADMIN");

    private final String role;
}