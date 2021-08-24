package com.onlinebanking.backend.persistent.domain;

import com.onlinebanking.TestUtils;
import com.onlinebanking.enums.RoleType;
import com.onlinebanking.shared.util.UserUtils;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit test for user.
 *
 * @author George on 7/9/2021
 * @version 1.0
 * @since 1.0
 */
class UserTest {
    private User client;
    private UserRole userRole1;

    @BeforeEach
    void runsBeforeEachTest() {
        client = UserUtils.createUser();
        userRole1 = new UserRole(client, new Role(RoleType.ROLE_CUSTOMER));
    }

    @Test
    void equalsContract() {
        User client = UserUtils.createUser();
        User admin = UserUtils.createUser();
        EqualsVerifier.forClass(User.class)
                .withRedefinedSuperclass()
                .withPrefabValues(User.class, client, admin)
                .withIgnoredFields(TestUtils.getIgnoredFields().toArray(new String[0]))
                .withIgnoredFields("password", "firstName", "lastName",
                        "phone", "verificationToken", "enabled", "userRoles", "userHistories", "savingsAccount",
                        "checkingAccount")
                .verify();
    }

    @Test
    void addSingleUserRoleToUser() {
        client.addUserRole(userRole1);
        assertEquals(1, client.getUserRoles().size());
        assertEquals(client, userRole1.getUser());
    }

    @Test
    void addDuplicateUserRoleToUserProduceSingleRole() {
        UserRole userRole2 = new UserRole(client, new Role(RoleType.ROLE_CUSTOMER));
        client.addUserRole(userRole1);
        client.addUserRole(userRole2);
        assertEquals(1, client.getUserRoles().size());
    }

    @Test
    void addTwoDifferentUserRoleToUserProduceMultipleRoles() {
        UserRole userRole2 = new UserRole(client, new Role(RoleType.ROLE_ADMIN));
        client.addUserRole(userRole1);
        client.addUserRole(userRole2);
        assertEquals(2, client.getUserRoles().size());
    }

    @Test
    void removeSingleUserRoleShouldReduceSizeByOne() {
        client.addUserRole(userRole1);
        assertEquals(1, client.getUserRoles().size());
        client.removeUserRole(userRole1);
        assertTrue(client.getUserRoles().isEmpty());
    }

    @Test
    void addSingleUserHistory() {
        UserHistory userHistory = new UserHistory();
        client.addUserHistory(userHistory);
        assertEquals(1, client.getUserHistories().size());
    }
}