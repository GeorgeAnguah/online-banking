package com.onlinebanking.backend.persistent.domain.account;

import com.onlinebanking.TestUtils;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class BaseAccountTest {

    @Test
    void equalsContract() {
        EqualsVerifier.forClass(BaseAccount.class)
                .withRedefinedSuperclass()
                .withIgnoredFields(TestUtils.getIgnoredFields().toArray(new String[0]))
                .verify();
    }

}