package com.onlinebanking.shared.util;

import com.onlinebanking.backend.service.security.EncryptionService;
import com.onlinebanking.constant.SecurityConstants;
import com.onlinebanking.enums.ErrorMessage;
import com.onlinebanking.enums.TokenType;
import com.onlinebanking.shared.util.validation.InputValidationUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;

/**
 * This class provides mechanism to create and manage cookies a text.
 *
 * @author Eric Opoku
 * @version 1.0
 * @since 1.0
 */
@Slf4j
public final class CookieUtils {

    private static final EncryptionService ENCRYPT_SERVICE = ApplicationContextUtils.getBean(EncryptionService.class);

    private CookieUtils() {
        throw new AssertionError(ErrorMessage.NOT_INSTANTIABLE.getErrorMsg());
    }

    /**
     * Creates a cookie with the specified token.
     *
     * @param token     the token
     * @param tokenType the type of token
     * @param duration  the duration till expiration
     *
     * @return the cookie
     */
    public static HttpCookie createTokenCookie(String token, TokenType tokenType, Long duration) {
        InputValidationUtils.validateInputs(CookieUtils.class, token, tokenType, duration);
        String encryptedToken = ENCRYPT_SERVICE.encrypt(token);

        return ResponseCookie
                .from(tokenType.getName(), encryptedToken)
                .sameSite(SecurityConstants.SAME_SITE)
                .sameSite("strict")
                .maxAge(duration)
                .httpOnly(true)
                .path("/")
                .build();
    }

    /**
     * Creates a cookie with the specified token.
     *
     * @param tokenType the token type
     *
     * @return the cookie
     */
    public static HttpCookie deleteTokenCookie(TokenType tokenType) {
        InputValidationUtils.validateInputs(CookieUtils.class, tokenType);

        return ResponseCookie
                .from(tokenType.getName(), "")
                .sameSite(SecurityConstants.SAME_SITE)
                .maxAge(0)
                .httpOnly(true)
                .path("/")
                .build();
    }

    /**
     * creates a cookie with the specified token and type with duration then adds it to the headers.
     *
     * @param httpHeaders the httpHeaders
     * @param tokenType   the tokenType
     * @param token       the token
     * @param duration    the duration
     */
    public static void addCookieToHeaders(HttpHeaders httpHeaders, TokenType tokenType, String token, long duration) {
        httpHeaders.add(HttpHeaders.SET_COOKIE, CookieUtils.createTokenCookie(token, tokenType, duration).toString());
    }

    /**
     * Deletes the specified token type from the provided headers.
     *
     * @param httpHeaders the httpHeaders
     * @param tokenType   the tokenType
     */
    public static void deleteCookie(HttpHeaders httpHeaders, TokenType tokenType) {
        httpHeaders.add(HttpHeaders.SET_COOKIE, CookieUtils.deleteTokenCookie(tokenType).toString());
    }
}
