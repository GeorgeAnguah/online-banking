package com.onlinebanking.backend.persistent.domain;

import com.onlinebanking.TestUtils;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

/**
 * Test class for CheckingAccount.
 *
 * @author Matthew Puentes on 8/21/2021
 * @version 1.0
 * @since 1.0
 */
class CheckingAccountTest {
    @Test
    void equalsContract() {
        EqualsVerifier.forClass(CheckingAccount.class)
                .withRedefinedSuperclass()
                .withIgnoredFields(TestUtils.getIgnoredFields().toArray(new String[0]))
                .verify();
    }
}