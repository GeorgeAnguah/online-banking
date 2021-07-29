package com.onlinebanking.backend.persistent.domain;

import com.onlinebanking.TestUtils;
import com.onlinebanking.shared.util.UserUtils;
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

        User client = UserUtils.createUser();
        User admin = UserUtils.createUser();

        EqualsVerifier.forClass(UserHistory.class)
                .withRedefinedSuperclass()
                .withPrefabValues(User.class, client, admin)
                .withIgnoredFields(TestUtils.getIgnoredFields().toArray(new String[0]))
                .verify();
    }

}