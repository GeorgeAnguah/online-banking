package com.onlinebanking.shared.util;

import com.onlinebanking.TestUtils;
import com.onlinebanking.enums.ErrorMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

class SecurityUtilsTest {

    @Test
    void callingPrivateConstructorShouldThrowException() {
        TestUtils.assertExceptionCause(
                SecurityUtils.class,
                AssertionError.class,
                ErrorMessage.NOT_INSTANTIABLE.getErrorMsg()
        );
    }

    @Test
    void testingIsUserAuthenticatedNotAuthenticated() {
        Assertions.assertFalse(SecurityUtils.isAuthenticated());
    }

    @Test
    void testingIsUserAuthenticatedAsAnonymous(TestInfo testInfo) {
        TestUtils.setAuthentication(testInfo.getDisplayName(), TestUtils.ANONYMOUS_USER);
        Assertions.assertFalse(SecurityUtils.isAuthenticated());
    }

    @Test
    void testingIsUserAuthenticatedAuthenticated(TestInfo testInfo) {
        TestUtils.setAuthentication(testInfo.getDisplayName(), TestUtils.ROLE_CUSTOMER);
        Assertions.assertTrue(SecurityUtils.isAuthenticated());
    }
}