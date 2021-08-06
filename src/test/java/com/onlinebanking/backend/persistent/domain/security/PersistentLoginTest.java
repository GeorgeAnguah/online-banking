package com.onlinebanking.backend.persistent.domain.security;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class PersistentLoginTest {

    @Test
    void equalsContract() {
        EqualsVerifier.forClass(PersistentLogin.class)
                .withIgnoredFields("series", "token", "lastUsed").verify();
    }

}