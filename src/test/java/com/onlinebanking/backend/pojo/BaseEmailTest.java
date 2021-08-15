package com.onlinebanking.backend.pojo;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class BaseEmailTest {

    private static final String[] IGNORED_FIELDS = {"recipients", "urls"};

    @Test
    void equalsContract() {
        EqualsVerifier.forClass(BaseEmail.class)
                .withRedefinedSuperclass()
                .withIgnoredFields(IGNORED_FIELDS)
                .verify();
    }

}