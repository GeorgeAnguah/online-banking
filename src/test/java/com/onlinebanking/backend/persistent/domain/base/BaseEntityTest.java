package com.onlinebanking.backend.persistent.domain.base;

import com.onlinebanking.TestUtils;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

/**
 * Test class for the BaseEntity.
 *
 * @author George on 6/25/2021
 * @version 1.0
 * @since 1.0
 */
class BaseEntityTest {

    @Test
    void equalsContract() {
        EqualsVerifier.forClass(BaseEntity.class)
                .withRedefinedSuperclass()
                .withIgnoredFields(TestUtils.getIgnoredFields().toArray(new String[0]))
                .verify();
    }
}