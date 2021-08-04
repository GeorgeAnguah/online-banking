package com.onlinebanking.constant;

import com.onlinebanking.TestUtils;
import com.onlinebanking.enums.ErrorMessage;
import org.junit.jupiter.api.Test;

class HomeConstantsTest {

    @Test
    void callingPrivateConstructorShouldThrowException() {
        TestUtils.assertExceptionCause(
                HomeConstants.class,
                AssertionError.class,
                ErrorMessage.NOT_INSTANTIABLE.getErrorMsg()
        );
    }

}