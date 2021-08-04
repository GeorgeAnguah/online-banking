package com.onlinebanking.shared.util;

import com.onlinebanking.OnlineBankingApplication;
import com.onlinebanking.TestUtils;
import com.onlinebanking.constant.ProfileTypeConstants;
import com.onlinebanking.enums.ErrorMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles(ProfileTypeConstants.TEST)
class ApplicationContextUtilsTest {

    @Test
    void callingConstructorShouldThrowException() {
        TestUtils.assertExceptionCause(
                ApplicationContextUtils.class,
                AssertionError.class
        );
    }

    @Test
    void callingConstructorShouldThrowExceptionWithMessage() {
        TestUtils.assertExceptionCause(
                ApplicationContextUtils.class,
                AssertionError.class,
                ErrorMessage.NOT_INSTANTIABLE.getErrorMsg()
        );
    }

    @Test
    void applicationContextShouldBeSetAfterApplicationStartup() {
        Assertions.assertNotNull(ApplicationContextUtils.getCtx());
    }

    @Test
    void testShouldRetrieveApplicationBeanAfterStart() {
        Assertions.assertNotNull(ApplicationContextUtils.getBean(OnlineBankingApplication.class));
    }
}