package com.onlinebanking.web.rest.v1;

import com.onlinebanking.IntegrationTestUtils;
import com.onlinebanking.TestUtils;
import com.onlinebanking.backend.service.UserService;
import com.onlinebanking.backend.service.security.CookieService;
import com.onlinebanking.backend.service.security.EncryptionService;
import com.onlinebanking.backend.service.security.JwtService;
import com.onlinebanking.constant.SecurityConstants;
import com.onlinebanking.enums.TokenType;
import com.onlinebanking.shared.dto.UserDto;
import com.onlinebanking.shared.util.UserUtils;
import com.onlinebanking.web.payload.request.LoginRequest;
import com.onlinebanking.web.payload.response.JwtResponseBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.Duration;

@AutoConfigureMockMvc
class AuthRestApiIntegrationTest extends IntegrationTestUtils {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CookieService cookieService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private EncryptionService encryptionService;

    private String loginUri;
    private String refreshUri;
    private String loginRequestJson;
    private UserDto storedUser;
    private Duration refreshTokenDuration;


    @BeforeEach
    void setUp(TestInfo testInfo) {
        var userDto = UserUtils.createUserDto(testInfo.getDisplayName(), true);
        storedUser = createAndAssertUser(userService, userDto);

        var loginRequest = new LoginRequest(storedUser.getUsername(), userDto.getPassword());
        loginRequestJson = TestUtils.toJson(loginRequest);

        String delimiter = "/";
        loginUri = String.join(delimiter, SecurityConstants.API_V1_AUTH_ROOT_URL, SecurityConstants.LOGIN);
        refreshUri = String.join(delimiter, SecurityConstants.API_V1_AUTH_ROOT_URL, SecurityConstants.REFRESH_TOKEN);
        refreshTokenDuration = Duration.ofDays(SecurityConstants.REFRESH_TOKEN_DURATION);
    }

    @Test
    void testLoginPathPreflightReturnsOk() throws Exception {
        performRequest(MockMvcRequestBuilders.options(loginUri))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testRefreshPathPreflightReturnsOk() throws Exception {
        performRequest(MockMvcRequestBuilders.options(refreshUri))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testLoginPathWithValidCredentialsReturnsOk() throws Exception {
        MvcResult mvcResult = performRequest(MockMvcRequestBuilders.post(loginUri))
                .andExpect(ResultMatcher.matchAll(expectedResponseDetails(storedUser)))
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        JwtResponseBuilder jwtResponseBuilder = TestUtils.parse(contentAsString, JwtResponseBuilder.class);

        String encryptedAccessToken = jwtResponseBuilder.getAccessToken();
        Assertions.assertFalse(jwtService.isValidJwtToken(encryptedAccessToken));

        String originalAccessToken = encryptionService.decrypt(encryptedAccessToken);
        Assertions.assertTrue(jwtService.isValidJwtToken(originalAccessToken));
    }

    @Test
    void testRefreshPathWithValidRefreshTokenReturnsOk() throws Exception {
        var jwtToken = jwtService.generateJwtToken(storedUser.getUsername());
        var cookie = cookieService.createTokenCookie(jwtToken, TokenType.REFRESH);

        performRequest(MockMvcRequestBuilders
                .get(refreshUri)
                .cookie(cookieService.createCookie(cookie)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Performs mockMvc request with the specified request details and content.
     *
     * @param request the request
     *
     * @return the result actions
     */
    private ResultActions performRequest(MockHttpServletRequestBuilder request) throws Exception {
        return mockMvc.perform(request
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .header(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "http://localhost:8080")
                .header(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, HttpMethod.POST)
                .contentType(MediaType.APPLICATION_JSON)
                .content(loginRequestJson));
    }

    /**
     * Returns the expected response details.
     *
     * @param userDto the userDto
     *
     * @return the result matchers
     */
    private ResultMatcher[] expectedResponseDetails(UserDto userDto) {
        long value = refreshTokenDuration.toSeconds();
        return new ResultMatcher[]{MockMvcResultMatchers.status().isOk(),
                MockMvcResultMatchers.cookie().exists(TokenType.REFRESH.getName()),
                MockMvcResultMatchers.cookie().httpOnly(TokenType.REFRESH.getName(), true),
                MockMvcResultMatchers.cookie().maxAge(TokenType.REFRESH.getName(), Math.toIntExact(value)),
                MockMvcResultMatchers.jsonPath("$").isMap(),
                MockMvcResultMatchers.jsonPath("$.username").value(userDto.getUsername()),
                MockMvcResultMatchers.jsonPath("$.publicId").value(userDto.getPublicId()),
                MockMvcResultMatchers.jsonPath("$.email").value(userDto.getEmail()),
                MockMvcResultMatchers.jsonPath("$.roles").value(UserUtils.getRoles(userDto.getUserRoles())),
                MockMvcResultMatchers.jsonPath("$.accessToken").exists()};
    }
}
