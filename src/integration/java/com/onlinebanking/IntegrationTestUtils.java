package com.onlinebanking;

import com.onlinebanking.backend.service.UserService;
import com.onlinebanking.constant.ProfileTypeConstants;
import com.onlinebanking.enums.RoleType;
import com.onlinebanking.shared.dto.UserDto;
import com.onlinebanking.shared.util.UserUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
@ActiveProfiles({ProfileTypeConstants.TEST})
public abstract class IntegrationTestUtils {

    /**
     * Creates and verify user with flexible field creation.
     *
     * @param userService the userService
     * @param username    if a custom user name is needed
     * @param enabled     if the user should be enabled
     *
     * @return persisted user
     */
    protected UserDto createAndAssertUser(UserService userService, String username, boolean enabled) {
        return createAndAssertUser(userService, username, false, enabled);
    }

    /**
     * Creates and verify user with flexible field creation.
     *
     * @param userService the userService
     * @param username    the username
     * @param isAdmin     if the user should be an admin
     * @param enabled     if the user should be enabled
     *
     * @return persisted user
     */
    protected UserDto createAndAssertUser(UserService userService, String username, boolean isAdmin, boolean enabled) {
        UserDto userDto = UserUtils.createUserDto(username);
        UserDto persistedUser = persistUser(userService, isAdmin, enabled, userDto);
        Assertions.assertNotNull(persistedUser);
        Assertions.assertNotNull(persistedUser.getId());
        return persistedUser;
    }

    /**
     * Creates and verify user with flexible field creation.
     *
     * @param userService the userService
     * @param userDto     the userDto
     *
     * @return persisted user
     */
    protected UserDto createAndAssertUser(UserService userService, UserDto userDto) {
        UserDto persistUser = persistUser(userService, false, userDto.isEnabled(), SerializationUtils.clone(userDto));
        Assertions.assertNotNull(persistUser);
        Assertions.assertNotNull(persistUser.getId());
        return persistUser;
    }

    protected UserDto persistUser(UserService userService, boolean isAdmin, boolean enabled, UserDto userDto) {
        Set<RoleType> roleTypes = new HashSet<>();
        if (isAdmin) {
            roleTypes.add(RoleType.ROLE_ADMIN);
        }
        roleTypes.add(RoleType.ROLE_CUSTOMER);

        if (enabled) {
            UserUtils.enableUser(userDto);
        }
        return userService.createUser(userDto, roleTypes);
    }
}
