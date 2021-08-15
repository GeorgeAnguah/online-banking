package com.onlinebanking.web.payload.response;

import com.onlinebanking.TestUtils;
import com.onlinebanking.backend.persistent.domain.Role;
import com.onlinebanking.backend.persistent.domain.User;
import com.onlinebanking.backend.persistent.domain.UserRole;
import com.onlinebanking.backend.service.impl.UserDetailsBuilder;
import com.onlinebanking.constant.SecurityConstants;
import com.onlinebanking.enums.RoleType;
import com.onlinebanking.shared.util.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

/**
 * Test JwtResponse builder class.
 *
 * @author George on 8/10/2021
 * @version 1.0
 * @since 1.0
 */
class JwtResponseBuilderTest {

    private UserDetailsBuilder userDetailsBuilder;
    private static final String JWT_TOKEN =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9."
            + "eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6InRlc3QgdXNlciIsImlhdCI6MTUxNjIzOTAyMn0."
            + "WUwx8SdGax2poWdgIbL3mMLamAVYb6fF1t87jSyGh94";

    @BeforeEach
    void setUp(TestInfo testInfo) {
        // create user from javaFaker
        User user = UserUtils.createUser(testInfo.getDisplayName(), testInfo.getDisplayName(),
                testInfo.getDisplayName() + TestUtils.TEST_EMAIL_SUFFIX, true);

        // create UserDetails from user
        userDetailsBuilder = UserDetailsBuilder.buildUserDetails(user);
    }

    @Test
    void shouldReturnEmptyJwtResponseWithNoUserDetails() {
        JwtResponseBuilder jwtResponse = JwtResponseBuilder.buildJwtResponse(JWT_TOKEN);
        Assertions.assertNotNull(jwtResponse);
        Assertions.assertTrue(StringUtils.isBlank(jwtResponse.getAccessToken()));
    }

    @Test
    void shouldReturnJwtResponseWithMatchingToken() {
        JwtResponseBuilder jwtResponse = JwtResponseBuilder.buildJwtResponse(JWT_TOKEN, userDetailsBuilder);
        Assertions.assertNotNull(jwtResponse);
        Assertions.assertEquals(JWT_TOKEN, jwtResponse.getAccessToken());

    }

    @Test
    void shouldReturnJwtResponseContainsUsername(TestInfo testInfo) {
        JwtResponseBuilder jwtResponse = JwtResponseBuilder.buildJwtResponse(JWT_TOKEN, userDetailsBuilder);
        Assertions.assertNotNull(jwtResponse);
        Assertions.assertEquals(testInfo.getDisplayName(), userDetailsBuilder.getUsername());

    }

    @Test
    void shouldReturnJwtResponseContainsPublicId() {
        JwtResponseBuilder jwtResponse = JwtResponseBuilder.buildJwtResponse(JWT_TOKEN, userDetailsBuilder);
        Assertions.assertNotNull(jwtResponse);
        Assertions.assertNotNull(userDetailsBuilder.getPublicId());
    }

    @Test
    void givenJwtTokenAndUserDetailsShouldBuildSuccessfully(TestInfo testInfo)  {
        var user = UserUtils.createUser(testInfo.getDisplayName());
        user.addUserRole(new UserRole(user, new Role(RoleType.ROLE_CUSTOMER)));

        var userDetails = UserDetailsBuilder.buildUserDetails(user);
        var jwtResponse = JwtResponseBuilder.buildJwtResponse(JWT_TOKEN, userDetails);

        Assertions.assertAll(() -> {
            Assertions.assertEquals(JWT_TOKEN, jwtResponse.getAccessToken());
            Assertions.assertEquals(user.getEmail(), jwtResponse.getEmail());
            Assertions.assertEquals(SecurityConstants.BEARER, jwtResponse.getType());

            Assertions.assertEquals(user.getUserRoles().size(), jwtResponse.getRoles().size());
            for (UserRole userRole : user.getUserRoles()) {
                Assertions.assertTrue(jwtResponse.getRoles().contains(userRole.getRole().getName()));
            }

        });

    }


}