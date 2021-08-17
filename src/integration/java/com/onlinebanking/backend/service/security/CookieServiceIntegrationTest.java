package com.onlinebanking.backend.service.security;

import com.onlinebanking.IntegrationTestUtils;
import com.onlinebanking.enums.TokenType;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;

class CookieServiceIntegrationTest extends IntegrationTestUtils {

    private static final int DURATION = 1;

    @Autowired
    private CookieService cookieService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private EncryptionService encryptionService;

    @Test
    void testGeneratesEncryptedCookieWithTokenThenDecrypt(TestInfo testInfo) {
        var jwtToken = jwtService.generateJwtToken(testInfo.getDisplayName());
        Assertions.assertTrue(jwtService.isValidJwtToken(jwtToken));

        var tokenCookie = cookieService.createTokenCookie(jwtToken, TokenType.ACCESS);
        String decryptedJwtToken = encryptionService.decrypt(tokenCookie.getValue());

        Assertions.assertEquals(jwtToken, decryptedJwtToken);
    }

    @Test
    void testCreateTokenCookieWithNullThrowsException() {
        Class<IllegalArgumentException> e = IllegalArgumentException.class;
        Assertions.assertThrows(e, () -> {
            Duration duration = Duration.ofHours(DURATION);
            cookieService.createTokenCookie(null, TokenType.ACCESS, duration);
        });
    }

    @Test
    void testGeneratesCookieTokenThenDeletesIt(TestInfo testInfo) {
        var jwtToken = jwtService.generateJwtToken(testInfo.getDisplayName());

        var tokenCookie = cookieService.createTokenCookie(jwtToken, TokenType.ACCESS, Duration.ofHours(DURATION));
        Assertions.assertTrue(StringUtils.isNotBlank(tokenCookie.getValue()));

        var httpCookie = cookieService.deleteTokenCookie(TokenType.ACCESS);
        Assertions.assertTrue(StringUtils.isBlank(httpCookie.getValue()));
    }

    @Test
    void testDeleteTokenCookieWithNullThrowsException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> cookieService.deleteTokenCookie(null));
    }

}