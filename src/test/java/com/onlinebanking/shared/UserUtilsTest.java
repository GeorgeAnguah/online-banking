package com.onlinebanking.shared;

import com.onlinebanking.backend.persistent.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

class UserUtilsTest {

    @Test
    void createUserWithNoArgument() {
        User user = UserUtils.createUser();
        Assertions.assertAll(() -> {
            Assertions.assertNotNull(user.getUsername());
            Assertions.assertNotNull(user.getPassword());
            Assertions.assertNotNull(user.getEmail());
            Assertions.assertNotNull(user.getPublicId());
        });
    }

    @Test
    @DisplayName("createUserWithNoArgument")
    void createUserWithAnArgument(TestInfo testInfo) {
        User user = UserUtils.createUser(testInfo.getDisplayName());
        Assertions.assertAll(() -> {
            Assertions.assertNotNull(user.getUsername());
            Assertions.assertNotNull(user.getPassword());
            Assertions.assertNotNull(user.getEmail());
            Assertions.assertNotNull(user.getPublicId());

            Assertions.assertEquals(testInfo.getDisplayName(), user.getUsername());
        });
    }

}