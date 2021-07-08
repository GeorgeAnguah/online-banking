package com.onlinebanking.backend.service.impl;

import com.onlinebanking.backend.persistent.domain.Role;
import com.onlinebanking.backend.persistent.domain.User;
import com.onlinebanking.backend.persistent.domain.UserRole;
import com.onlinebanking.enums.RoleType;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.security.core.userdetails.UserDetails;

class UserDetailsBuilderTest {

    private User user;

    @BeforeEach
    void setUp(TestInfo testInfo) {
        user = new User();
        user.setUsername(testInfo.getDisplayName());
        user.setPassword(testInfo.getDisplayName());
        user.setEmail(testInfo.getDisplayName().concat("@email.com"));
        user.addUserRole(new UserRole(user, new Role(RoleType.CUSTOMER)));
    }

    @Test
    void shouldSuccessfullyBuildAUserDetailsGivenUser(TestInfo testInfo) {
        var userDetails = UserDetailsBuilder.buildUserDetails(user);

        Assertions.assertAll(() -> {
            Assertions.assertNotNull(userDetails);
            Assertions.assertTrue(userDetails.isAccountNonExpired());
            Assertions.assertTrue(userDetails.isAccountNonLocked());
            Assertions.assertTrue(userDetails.isCredentialsNonExpired());
            Assertions.assertFalse(userDetails.isEnabled());
            MatcherAssert.assertThat(userDetails, CoreMatchers.instanceOf(UserDetails.class));
            Assertions.assertEquals(testInfo.getDisplayName(), userDetails.getUsername());
        });
    }

    @Test
    void shouldThrowExceptionWhenInputIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> UserDetailsBuilder.buildUserDetails(null));
    }

}