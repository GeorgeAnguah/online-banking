package com.onlinebanking.backend.persistent.domain;

import com.onlinebanking.TestUtils;
import com.onlinebanking.backend.persistent.domain.account.SavingsAccount;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

/**
 * Test class for SavingsAccount.
 *
 * @author Matthew Puentes on 8/22/2021
 * @version 1.0
 * @since 1.0
 */
class SavingsAccountTest {
    @Test
    void equalsContract() {
        EqualsVerifier.forClass(SavingsAccount.class)
                .withRedefinedSuperclass()
                .withIgnoredFields(TestUtils.getIgnoredFields().toArray(new String[0]))
                .verify();
    }
}