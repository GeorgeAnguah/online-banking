package com.onlinebanking.shared.util;

import com.onlinebanking.TestUtils;
import com.onlinebanking.enums.ErrorMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StringUtilsTest {

    private static final int LENGTH = 5;
    private static final int DEFAULT_LENGTH = 30;

    @Test
    void callingPrivateConstructorShouldThrowException() {
        TestUtils.assertExceptionCause(
                StringUtils.class,
                AssertionError.class,
                ErrorMessage.NOT_INSTANTIABLE.getErrorMsg()
        );
    }

    @Test
    void generateUserIdWithCustomLength() {
        String userId = StringUtils.generateAlphaNumericId(LENGTH);
        Assertions.assertEquals(LENGTH, userId.length());
    }

    @Test
    void generateUserIdWithDefaultLength() {
        String userId = StringUtils.generatePublicId();
        Assertions.assertEquals(DEFAULT_LENGTH, userId.length());
    }

}