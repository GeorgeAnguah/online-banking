package com.onlinebanking.backend.persistent.domain.base;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.internal.util.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

/**
 * Test class for the BaseEntity.
 *
 * @author George on 6/25/2021
 * @version 1.0
 * @since 1.0
 */
class BaseEntityTest {

    BaseEntity baseEntity;

    @BeforeEach
    void setUp(TestInfo testInfo) {
        baseEntity = new BaseEntity();
        baseEntity.setId(1L);
        baseEntity.setVersion(1);
    }

    @Test
    void testEqualsContract() {
        EqualsVerifier.simple().forClass(BaseEntity.class)
                .withIgnoredFields("id", "createdAt", "createdBy", "updatedAt", "updatedBy").verify();
    }
}