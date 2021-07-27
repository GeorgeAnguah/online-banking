package com.onlinebanking.shared;

import com.onlinebanking.TestUtils;
import com.onlinebanking.backend.persistent.domain.User;
import com.onlinebanking.enums.ErrorMessage;
import com.onlinebanking.shared.util.validation.InputValidationUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

class UserUtilsTest {

    @Test
    void callingConstructorShouldThrowException() {
        TestUtils.assertExceptionCause(
                UserUtils.class,
                AssertionError.class
        );
    }

    @Test
    void callingConstructorShouldThrowExceptionWithMessage() {
        TestUtils.assertExceptionCause(
                UserUtils.class,
                AssertionError.class,
                ErrorMessage.NOT_INSTANTIABLE.getErrorMsg()
        );
    }

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

    @Test
    void convertToUserDto() {
        User user = UserUtils.createUser();
        var userDto = UserUtils.convertToUserDto(user);

        Assertions.assertAll(() -> {
            Assertions.assertEquals(user.getUsername(), userDto.getUsername());
            Assertions.assertNotNull(userDto.getPassword());
            Assertions.assertNotNull(userDto.getEmail());
            Assertions.assertNotNull(userDto.getPublicId());
        });
    }

    @Test
    void convertToUserDtoWithNullCollections() {
        User user = UserUtils.createUser();
        user.setUserRoles(null);
        user.setUserHistories(null);
        var userDto = UserUtils.convertToUserDto(user);

        Assertions.assertAll(() -> {
            Assertions.assertNotNull(userDto.getUserRoles());
            Assertions.assertNotNull(userDto.getUserHistories());
        });
    }

    @Test
    void shouldThrowExceptionWhenUserInputIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> UserUtils.convertToUserDto(null));
    }

    @Test
    void convertToUser() {
        User user = UserUtils.createUser();
        var userDto = UserUtils.convertToUserDto(user);

        var userFromUserDto = UserUtils.convertToUser(userDto);
        Assertions.assertEquals(user, userFromUserDto);
    }

    @Test
    void convertToUserWithNullCollections() {
        User user = UserUtils.createUser();
        var userDto = UserUtils.convertToUserDto(user);
        userDto.setUserRoles(null);
        userDto.setUserHistories(null);

        var userFromUserDto = UserUtils.convertToUser(userDto);
        Assertions.assertAll(() -> {
            Assertions.assertNotNull(userFromUserDto.getUserRoles());
            Assertions.assertNotNull(userFromUserDto.getUserHistories());
        });
    }

    @Test
    void shouldThrowExceptionWhenUserDtoInputIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> UserUtils.convertToUser(null));
    }
}