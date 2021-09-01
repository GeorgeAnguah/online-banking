package com.onlinebanking.backend.service.security.impl;

import com.onlinebanking.backend.service.security.CookieService;
import com.onlinebanking.backend.service.security.EncryptionService;
import com.onlinebanking.constant.ProfileTypeConstants;
import com.onlinebanking.constant.SecurityConstants;
import com.onlinebanking.enums.TokenType;
import com.onlinebanking.shared.util.validation.InputValidationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.time.Duration;
import java.util.Arrays;
import java.util.Objects;

/**
 * This is the implementation of the cookie service.
 *
 * @author Eric Opoku
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CookieServiceImpl implements CookieService {

    private final Environment environment;
    private final EncryptionService encryptionService;

    private final Duration duration = Duration.ofDays(SecurityConstants.REFRESH_TOKEN_DURATION);

    /**
     * Creates a servlet cookie from spring httpCookie.
     *
     * @param httpCookie the httpCookie
     *
     * @return the cookie
     */
    @Override
    public Cookie createCookie(HttpCookie httpCookie) {
        InputValidationUtils.validateInputs(getClass(), httpCookie);
        var cookie = new Cookie(httpCookie.getName(), httpCookie.getValue());
        cookie.setSecure(Arrays.asList(environment.getActiveProfiles()).contains(ProfileTypeConstants.PROD));
        cookie.setHttpOnly(true);

        return cookie;
    }

    /**
     * Creates an httpOnly httpCookie.
     *
     * @param name     the name of the cookie
     * @param value    the value of the cookie
     * @param duration the duration till expiration
     *
     * @return the cookie
     */
    @Override
    public HttpCookie createCookie(String name, String value, Duration duration) {
        InputValidationUtils.validateInputs(getClass(), name);

        return ResponseCookie
                .from(name, value)
                .secure(Arrays.asList(environment.getActiveProfiles()).contains(ProfileTypeConstants.PROD))
                .sameSite(SecurityConstants.SAME_SITE)
                .path(SecurityConstants.ROOT_PATH)
                .maxAge(Objects.isNull(duration) ? this.duration : duration)
                .httpOnly(true)
                .build();
    }

    /**
     * Creates a cookie with the specified token and token type.
     *
     * @param token     the token
     * @param tokenType the type of token
     *
     * @return the cookie
     */
    @Override
    public HttpCookie createTokenCookie(String token, TokenType tokenType) {
        InputValidationUtils.validateInputs(getClass(), token);
        return createTokenCookie(token, tokenType, duration);
    }

    /**
     * Creates a cookie with the specified token and token type.
     *
     * @param token     the token
     * @param tokenType the type of token
     * @param duration  the duration till expiration
     *
     * @return the cookie
     */
    @Override
    public HttpCookie createTokenCookie(String token, TokenType tokenType, Duration duration) {
        InputValidationUtils.validateInputs(getClass(), token, tokenType, duration);

        var encryptedToken = encryptionService.encrypt(token);
        return createCookie(tokenType.getName(), encryptedToken, duration);
    }

    /**
     * Creates a cookie with the specified token.
     *
     * @param tokenType the token type
     *
     * @return the cookie
     */
    @Override
    public HttpCookie deleteTokenCookie(TokenType tokenType) {
        InputValidationUtils.validateInputs(getClass(), tokenType);
        return createCookie(tokenType.getName(), StringUtils.EMPTY, Duration.ZERO);
    }

    /**
     * Creates a cookie with the specified token.
     *
     * @param tokenType the token type
     *
     * @return the httpHeaders
     */
    @Override
    public HttpHeaders addDeletedCookieToHeaders(TokenType tokenType) {
        HttpCookie httpCookie = deleteTokenCookie(tokenType);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.SET_COOKIE, httpCookie.toString());
        return httpHeaders;
    }

    /**
     * creates a cookie with the specified token and tokenType then adds it to headers.
     *
     * @param tokenType the tokenType
     * @param token     the token
     *
     * @return the httpHeaders
     */
    @Override
    public HttpHeaders addCookieToHeaders(TokenType tokenType, String token) {
        return addCookieToHeaders(tokenType, token, duration);
    }

    /**
     * creates a cookie with the specified token and tokenType then adds it to headers.
     *
     * @param tokenType the tokenType
     * @param token     the token
     * @param duration  the duration till expiration
     *
     * @return the httpHeaders
     */
    @Override
    public HttpHeaders addCookieToHeaders(TokenType tokenType, String token, Duration duration) {
        var httpHeaders = new HttpHeaders();
        addCookieToHeaders(httpHeaders, tokenType, token, Objects.isNull(duration) ? this.duration : duration);

        return httpHeaders;
    }

    /**
     * creates a cookie with the specified token and type with duration then adds it to the headers.
     *
     * @param httpHeaders the httpHeaders
     * @param tokenType   the tokenType
     * @param token       the token
     * @param duration    the duration till expiration
     */
    @Override
    public void addCookieToHeaders(HttpHeaders httpHeaders, TokenType tokenType, String token, Duration duration) {
        httpHeaders.add(HttpHeaders.SET_COOKIE, createTokenCookie(token, tokenType, duration).toString());
    }
}
