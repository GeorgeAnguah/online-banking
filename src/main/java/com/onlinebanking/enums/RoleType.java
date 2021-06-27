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
public enum RoleType {
    ADMIN(1,"ROLE_ADMIN"),
    CUSTOMER(2,"ROLE_CUSTOMER");

    private final int id;
    private final String role;
}