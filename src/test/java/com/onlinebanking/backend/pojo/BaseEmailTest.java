package com.onlinebanking.backend.pojo;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.Test;

class BaseEmailTest {

    private static final String[] IGNORED_FIELDS = {"recipients", "urls"};

    @Test
    void equalsContract() {
        EqualsVerifier.forClass(BaseEmail.class)
                .withRedefinedSuperclass()
                .withIgnoredFields(IGNORED_FIELDS)
                .suppress(Warning.NONFINAL_FIELDS)
                .verify();
    }

}