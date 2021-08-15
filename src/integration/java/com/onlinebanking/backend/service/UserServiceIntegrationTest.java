package com.onlinebanking.backend.service;

import com.onlinebanking.IntegrationTestUtils;
import com.onlinebanking.backend.persistent.domain.Role;
import com.onlinebanking.enums.RoleType;
import com.onlinebanking.shared.dto.UserDto;
import com.onlinebanking.shared.util.UserUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

class UserServiceIntegrationTest extends IntegrationTestUtils {

    @Autowired
    private UserService userService;

    /**
     * Test attempts to create a user with provided roles, verify that user has been created successfully by checking
     * assigned id.
     */
    @Test
    @DisplayName("createUserWithSpecifiedRoles")
    void createUserWithSpecifiedRoles(TestInfo testInfo) {
        Assertions.assertDoesNotThrow(() -> createAndAssertUser(userService, testInfo.getDisplayName(), true, false));
    }

    /**
     * Test attempts to create a user, verify that user has been created successfully by checking assigned id.
     */
    @Test
    @DisplayName("createUserWithoutSpecifiedRoles")
    void createUserWithoutSpecifiedRoles(TestInfo testInfo) {
        var userDto = UserUtils.createUserDto(testInfo.getDisplayName());
        var persistedUserDto = userService.createUser(userDto);

        Assertions.assertAll(() -> {
            Assertions.assertNotNull(persistedUserDto);
            Assertions.assertNotNull(persistedUserDto.getId());
            Assertions.assertEquals(userDto, persistedUserDto);

            // assert that the user now has a new CUSTOMER role assigned after creation.
            Assertions.assertFalse(persistedUserDto.getUserRoles()
                    .stream()
                    .filter(userRole -> userRole.getRole().equals(new Role(RoleType.ROLE_CUSTOMER)))
                    .collect(Collectors.toSet())
                    .isEmpty());
        });
    }

    /**
     * Creating a user who exists and not enabled should return the existing user.
     */
    @Test
    @DisplayName("createUserAlreadyExistingAndNotEnabled")
    void createUserAlreadyExistingAndNotEnabled(TestInfo testInfo) {
        UserDto userDto = createAndAssertUser(userService, testInfo.getDisplayName(), false);
        UserDto existingUser = createAndAssertUser(userService, userDto);
        Assertions.assertEquals(userDto, existingUser);
    }

    /**
     * Creating a user who exists and enabled should return null.
     */
    @Test
    @DisplayName("createUserAlreadyExistingAndEnabled")
    void createUserAlreadyExistingAndEnabled(TestInfo testInfo) {
        UserDto userDto = createAndAssertUser(userService, testInfo.getDisplayName(), true);
        UserDto existingUser = persistUser(userService, false, true, userDto);
        Assertions.assertNull(existingUser);
    }

    /**
     * Test checks that an existing user can be retrieved using the username provided.
     */
    @Test
    @DisplayName("getUserByUsername")
    void getUserByUsername(TestInfo testInfo) {
        UserDto userDto = createAndAssertUser(userService, testInfo.getDisplayName(), false);
        UserDto storedUser = userService.findByUsername(userDto.getUsername());
        Assertions.assertEquals(userDto, storedUser);
    }

    @Test
    @DisplayName("getUserByUsernameNotExisting")
    void getUserByUsernameNotExisting(TestInfo testInfo) {
        UserDto userByUsername = userService.findByUsername(testInfo.getDisplayName());
        Assertions.assertNull(userByUsername);
    }

    @Test
    @DisplayName("getUserByEmail")
    void getUserByEmail(TestInfo testInfo) {
        UserDto userDto = createAndAssertUser(userService, testInfo.getDisplayName(), false);
        UserDto userByEmail = userService.findByEmail(userDto.getEmail());
        Assertions.assertEquals(userDto, userByEmail);
    }

    @Test
    @DisplayName("getUserByEmailNotExisting")
    void getUserByEmailNotExisting(TestInfo testInfo) {
        UserDto userByEmail = userService.findByEmail(testInfo.getDisplayName());
        Assertions.assertNull(userByEmail);
    }
}