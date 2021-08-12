package com.onlinebanking.shared.util;

import com.onlinebanking.TestUtils;
import com.onlinebanking.backend.service.JwtService;
import com.onlinebanking.backend.service.security.EncryptionService;
import com.onlinebanking.constant.ProfileTypeConstants;
import com.onlinebanking.enums.ErrorMessage;
import com.onlinebanking.enums.TokenType;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpCookie;
import org.springframework.test.context.ActiveProfiles;

import java.time.Duration;

@SpringBootTest
@ActiveProfiles(ProfileTypeConstants.TEST)
class CookieUtilsTest {

    private static final int DURATION = 1;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private EncryptionService encryptionService;

    @Test
    void callingConstructorShouldThrowException() {
        TestUtils.assertExceptionCause(
                CookieUtils.class,
                AssertionError.class
        );
    }

    @Test
    void callingConstructorShouldThrowExceptionWithMessage() {
        TestUtils.assertExceptionCause(
                CookieUtils.class,
                AssertionError.class,
                ErrorMessage.NOT_INSTANTIABLE.getErrorMsg()
        );
    }

    @Test
    void testGeneratesEncryptedCookieWithTokenThenDecrypt(TestInfo testInfo) {
        var jwtToken = jwtService.generateJwtToken(testInfo.getDisplayName());
        Assertions.assertTrue(jwtService.isValidJwtToken(jwtToken));

        HttpCookie tokenCookie = CookieUtils.createTokenCookie(jwtToken, TokenType.ACCESS, Duration.ofHours(DURATION));
        String cookieValue = tokenCookie.getValue();
        String decryptedJwtToken = encryptionService.decrypt(cookieValue);

        Assertions.assertEquals(jwtToken, decryptedJwtToken);
    }

    @Test
    void testCreateTokenCookieWithNullThrowsException() {

        Class<IllegalArgumentException> e = IllegalArgumentException.class;
        Assertions.assertThrows(e, () -> {
            Duration duration = Duration.ofHours(DURATION);
            CookieUtils.createTokenCookie(null, TokenType.ACCESS, duration);
        });
    }

    @Test
    void testGeneratesCookieTokenThenDeletesIt(TestInfo testInfo) {
        var jwtToken = jwtService.generateJwtToken(testInfo.getDisplayName());

        HttpCookie tokenCookie = CookieUtils.createTokenCookie(jwtToken, TokenType.ACCESS, Duration.ofHours(DURATION));
        Assertions.assertTrue(StringUtils.isNotBlank(tokenCookie.getValue()));

        HttpCookie httpCookie = CookieUtils.deleteTokenCookie(TokenType.ACCESS);
        Assertions.assertTrue(StringUtils.isBlank(httpCookie.getValue()));
    }

    @Test
    void testDeleteTokenCookieWithNullThrowsException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> CookieUtils.deleteTokenCookie(null));
    }
}