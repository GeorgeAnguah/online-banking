package com.onlinebanking.backend.persistent.domain.base;

import com.onlinebanking.TestUtils;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

class OnlineBankingAuditorAwareTest {

    private static final String SYSTEM_USER = "system";

    @BeforeEach
    void setUp() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void getCurrentAuditor() {
        Assertions.assertEquals(SYSTEM_USER, getAuditor());
    }

    @Test
    void getCurrentAuditorWithNoAuthentication() {
        TestUtils.setAuthentication(TestUtils.ANONYMOUS_ROLE, TestUtils.ANONYMOUS_USER);
        SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
        Assertions.assertEquals(SYSTEM_USER, getAuditor());
    }

    @Test
    void getCurrentAuditorWithAnonymousUser() {
        TestUtils.setAuthentication(TestUtils.ANONYMOUS_ROLE, TestUtils.ANONYMOUS_USER);
        Assertions.assertEquals(SYSTEM_USER, getAuditor());
    }

    @Test
    void getCurrentAuditorWithAuthenticatedUser(TestInfo testInfo) {
        TestUtils.setAuthentication(TestUtils.ROLE_CUSTOMER, testInfo.getDisplayName());
        Assertions.assertEquals(getAuditor(), testInfo.getDisplayName());
    }

    private String getAuditor() {
        var onlineBankingAuditorAware = new OnlineBankingAuditorAware();
        Optional<String> currentAuditor = onlineBankingAuditorAware.getCurrentAuditor();
        return currentAuditor.orElse(null);
    }

    @Test
    void equalsContract() {
        EqualsVerifier.forClass(OnlineBankingAuditorAware.class)
                .verify();
    }

}