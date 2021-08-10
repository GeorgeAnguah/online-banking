package com.onlinebanking.web.payload.response;

import com.onlinebanking.backend.persistent.domain.User;
import com.onlinebanking.backend.service.impl.UserDetailsBuilder;
import com.onlinebanking.shared.util.UserUtils;
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
class JwtResponseTest {

    private UserDetailsBuilder userDetailsBuilder;
    private static String fakeJwToken =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9."
            + "eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6InRlc3QgdXNlciIsImlhdCI6MTUxNjIzOTAyMn0."
            + "WUwx8SdGax2poWdgIbL3mMLamAVYb6fF1t87jSyGh94";

    @BeforeEach
    void setUp(TestInfo testInfo) {
        // create user from javaFaker
        User user = UserUtils.createUser(testInfo.getDisplayName(), testInfo.getDisplayName(),
                testInfo.getDisplayName() + "@gmail.com", true);

        // create UserDetails from user
        userDetailsBuilder = UserDetailsBuilder.buildUserDetails(user);
    }

    @Test
    void shouldReturnJwtResponseWithMatchingToken() {
        JwtResponse jwtResponse = JwtResponse.buildJwtResponse(fakeJwToken, userDetailsBuilder);
        Assertions.assertNotNull(jwtResponse);
        Assertions.assertEquals(fakeJwToken, jwtResponse.getAccessToken());

    }

    @Test
    void shouldReturnJwtResponseContainsUsername(TestInfo testInfo) {
        JwtResponse jwtResponse = JwtResponse.buildJwtResponse(fakeJwToken, userDetailsBuilder);
        Assertions.assertNotNull(jwtResponse);
        Assertions.assertEquals(testInfo.getDisplayName(), userDetailsBuilder.getUsername());

    }


}