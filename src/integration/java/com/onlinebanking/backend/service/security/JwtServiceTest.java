package com.onlinebanking.backend.service.security;

import com.onlinebanking.backend.service.security.JwtService;
import com.onlinebanking.constant.ProfileTypeConstants;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;

@SpringBootTest
@ActiveProfiles(value = {ProfileTypeConstants.TEST})
class JwtServiceTest {

    private enum TokenType {
        BAD_SIGNATURE, MALFORMED, UNSUPPORTED
    }

    @Autowired
    private JwtService jwtService;

    @Test
    void generateJwtTokenThenValidate(TestInfo testInfo) {
        var jwtToken = jwtService.generateJwtToken(testInfo.getDisplayName());
        Assertions.assertTrue(jwtService.isValidJwtToken(jwtToken));
    }

    @Test
    void generateJwtTokenWithNullThrowsException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> jwtService.generateJwtToken(null));
    }

    @Test
    void generateJwtTokenThenVerifyUsername(TestInfo testInfo) {
        String jwtToken = jwtService.generateJwtToken(testInfo.getDisplayName());

        Assertions.assertEquals(testInfo.getDisplayName(), jwtService.getUsernameFromToken(jwtToken));
    }

    @Test
    void getUsernameFromTokenWithNullThrowsException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> jwtService.getUsernameFromToken(null));
    }

    @Test
    void generateExpiredJwtTokenThenValidate(TestInfo testInfo) {
        // Basically setting current date - 2 which is two days before today.
        int twoDaysPast = -2;
        var expirationDate = DateUtils.addDays(new Date(), twoDaysPast);
        String jwtToken = jwtService.generateJwtToken(testInfo.getDisplayName(), expirationDate);

        Assertions.assertFalse(jwtService.isValidJwtToken(jwtToken));
    }

    @Test
    void isValidJwtTokenWithIllegalArgument() {
        Assertions.assertFalse(jwtService.isValidJwtToken(null));
    }

    @Test
    void isValidJwtTokenWithBadSignatureJwtToken(TestInfo testInfo) {
        var badSignatureJwtToken = getJwtTokenByType(TokenType.BAD_SIGNATURE, testInfo);
        Assertions.assertFalse(jwtService.isValidJwtToken(badSignatureJwtToken));
    }

    @Test
    void isValidJwtTokenWithMalformedJwtToken(TestInfo testInfo) {
        var malformedJwtToken = getJwtTokenByType(TokenType.MALFORMED, testInfo);
        Assertions.assertFalse(jwtService.isValidJwtToken(malformedJwtToken));
    }

    @Test
    void isValidJwtTokenWithUnsupportedJwtToken(TestInfo testInfo) {
        var unSupportedJwtToken = getJwtTokenByType(TokenType.UNSUPPORTED, testInfo);
        Assertions.assertFalse(jwtService.isValidJwtToken(unSupportedJwtToken));
    }

    /**
     * Generate an invalid jwt token based on the type provided.
     * Jwt has the format header(algorithm).payload.signature
     *
     * @param tokenType the token type
     * @param testInfo  the testInfo
     *
     * @return the jwt token
     */
    private String getJwtTokenByType(TokenType tokenType, TestInfo testInfo) {
        var jwtToken = jwtService.generateJwtToken(testInfo.getDisplayName());
        var separatedJwtToken = jwtToken.split("\\.");
        var header = separatedJwtToken[0];
        var payload = separatedJwtToken[1];
        var signature = separatedJwtToken[2];

        var delimiter = ".";
        if (tokenType == TokenType.BAD_SIGNATURE) {
            return String.join(delimiter, header, payload.substring(payload.length() / 2), signature);
        } else if (tokenType == TokenType.MALFORMED) {
            return String.join(delimiter, header, payload);
        } else if (tokenType == TokenType.UNSUPPORTED) {
            return String.join(delimiter, header, payload, "");
        }
        return null;
    }
}