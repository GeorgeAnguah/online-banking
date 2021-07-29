package com.onlinebanking.backend.persistent.domain;

import com.onlinebanking.TestUtils;
import com.onlinebanking.enums.RoleType;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

/**
 * Unit test for UserRole.
 *
 * @author George on 7/28/2021
 * @version 1.0
 * @since 1.0
 */
class UserRoleTest {

    @Test
    void equalsContract() {
        User client = TestUtils.createUser("one");
        User admin = TestUtils.createUser("two");

        Role roleClient = new Role(RoleType.CUSTOMER);
        Role roleAdmin = new Role(RoleType.ADMIN);


        EqualsVerifier.forClass(UserRole.class)
                .withRedefinedSuperclass()
                .withPrefabValues(User.class, client, admin)
                .withPrefabValues(Role.class, roleClient, roleAdmin)
                .withIgnoredFields(TestUtils.getIgnoredFields().toArray(new String[0]))
                .verify();
    }

}