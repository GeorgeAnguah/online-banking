package com.onlinebanking.backend.persistent.domain;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

/**
 * Test class for Role.
 *
 * @author George on 6/26/2021
 * @version 1.0
 * @since 1.0
 */
class RoleTest {

    @Test
    void equalsContract() {
        EqualsVerifier.forClass(Role.class)
                .withIgnoredFields("id").verify();
    }
}