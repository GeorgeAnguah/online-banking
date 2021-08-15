package com.onlinebanking.backend.persistent.domain.security;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

class PersistentLoginTest {

    @Test
    void testCreatePersistentLoginThenVerifyFieldsSet(TestInfo testInfo) {
        var persistence = new PersistentLogin();
        persistence.setSeries(testInfo.getDisplayName());

        Assertions.assertNotNull(persistence.getSeries());
    }

    @Test
    void equalsContract() {
        EqualsVerifier.forClass(PersistentLogin.class)
                .withIgnoredFields("series", "token", "lastUsed").verify();
    }

}