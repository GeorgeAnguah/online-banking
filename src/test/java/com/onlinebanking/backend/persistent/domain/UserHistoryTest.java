package com.onlinebanking.backend.persistent.domain;

import com.onlinebanking.TestUtils;
import com.onlinebanking.backend.persistent.domain.base.BaseEntity;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

/**
 * Test class for UserHistory.
 *
 * @author George on 7/3/2021
 * @version 1.0
 * @since 1.0
 */
class UserHistoryTest {

    @Test
    void equalsContract() {
        User client = createUser("one");
        User admin = createUser("two");

        EqualsVerifier.forClass(BaseEntity.class)
                .withRedefinedSubclass(UserHistory.class)
                .withIgnoredFields(TestUtils.getIgnoredFields().toArray(new String[0]))
                .verify();

        EqualsVerifier.forClass(UserHistory.class)
                .withRedefinedSuperclass()
                .withPrefabValues(User.class, client, admin)
                .withIgnoredFields(TestUtils.getIgnoredFields().toArray(new String[0]))
                .verify();
    }

    private User createUser(String username) {
        User user = new User();
        user.setUsername(username);
        user.setPublicId(username + "publicId");
        user.setEmail(username + "@email.com");
        user.setPassword(username);
        return user;
    }
}