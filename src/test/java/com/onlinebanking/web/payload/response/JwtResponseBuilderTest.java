package com.onlinebanking.web.payload.response;

import com.onlinebanking.IntegrationTestUtils;
import com.onlinebanking.backend.persistent.domain.UserRole;
import com.onlinebanking.backend.service.JwtService;
import com.onlinebanking.backend.service.UserService;
import com.onlinebanking.backend.service.impl.UserDetailsBuilder;
import com.onlinebanking.constant.SecurityConstants;
import com.onlinebanking.shared.dto.UserDto;
import com.onlinebanking.shared.util.UserUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;

class JwtResponseBuilderTest extends IntegrationTestUtils {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Test
    @DisplayName("givenJwtTokenAndUserDetailsShouldBuildSuccessfully")
    void givenJwtTokenAndUserDetailsShouldBuildSuccessfully(TestInfo testInfo)  {
        UserDto userDto = UserUtils.createUserDto(testInfo.getDisplayName());
        UserDto user = createUser(userService, userDto);

        String jwtToken = jwtService.generateJwtToken(user.getUsername());
        UserDetailsBuilder userDetails = (UserDetailsBuilder) userDetailsService.loadUserByUsername(user.getUsername());

        JwtResponseBuilder jwtResponseBuilder = JwtResponseBuilder.buildJwtResponse(jwtToken, userDetails);

        Assertions.assertAll(() -> {
            Assertions.assertEquals(jwtToken, jwtResponseBuilder.getAccessToken());
            Assertions.assertEquals(user.getUsername(), jwtResponseBuilder.getUsername());
            Assertions.assertEquals(user.getPublicId(), jwtResponseBuilder.getPublicId());
            Assertions.assertEquals(user.getEmail(), jwtResponseBuilder.getEmail());
            Assertions.assertEquals(SecurityConstants.BEARER, jwtResponseBuilder.getType());

            Assertions.assertEquals(user.getUserRoles().size(), jwtResponseBuilder.getRoles().size());
            for (UserRole userRole : user.getUserRoles()) {
                Assertions.assertTrue(jwtResponseBuilder.getRoles().contains(userRole.getRole().getName()));
            }

        });

    }
}