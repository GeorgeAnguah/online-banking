package com.onlinebanking.constant;

import com.onlinebanking.enums.ErrorMessage;
import org.springframework.http.HttpHeaders;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * This class holds all security-related URL mappings constants.
 *
 * @author George on 6/20/2021
 * @version 1.0
 * @since 1.0
 */
public final class SecurityConstants {

    public static final String ACCOUNT_OVERVIEW = "/account-overview";
    public static final String API_AUTH_ROOT_URL = "/api/auth";
    public static final String API_AUTH_URL_MAPPING = "/api/auth/**";
    public static final String API_ROOT_URL_MAPPING = "/api/**";
    public static final String BEARER = "Bearer";
    public static final String BEARER_PREFIX = "Bearer ";
    public static final String H2_CONSOLE_URL_MAPPING = "/console/*";
    public static final String JSESSIONID = "JSESSIONID";
    public static final String LOGIN_LOGOUT = "/?logout";
    public static final String LOGIN_ERROR = "/?error";
    public static final String LOGOUT = "/logout";
    public static final String LOGIN = "/login";
    public static final String REFRESH = "/refresh";
    public static final String REMEMBER_ME = "remember-me";
    public static final String ROOT_PATH = "/";
    public static final String SAME_SITE = "strict";

    public static final int SECURITY_STRENGTH = 12;

    private static final String[] PUBLIC_MATCHERS = {
            HomeConstants.INDEX_URL_MAPPING,
            "/css/**",
            "/js/**",
            "/images/**",
            "/fonts/**",
            "/webjars/**",
            "/resources/**",
            "/static/**",
            "/console/**"
    };

    public static final List<String> ALLOWED_HTTP_METHODS = List.of(
            "GET", "POST", "PUT", "DELETE","OPTIONS", "PATCH"
    );

    public static final List<String> ALLOWED_HTTP_HEADERS = List.of(
            HttpHeaders.AUTHORIZATION, HttpHeaders.CACHE_CONTROL, HttpHeaders.CONTENT_TYPE
    );

    private SecurityConstants() {
        throw new AssertionError(ErrorMessage.NOT_INSTANTIABLE.getErrorMsg());
    }

    /**
     * Public matchers to allow access to the application.
     *
     * @return public matchers.
     */
    public static Collection<String> getPublicMatchers() {
        return Collections.unmodifiableCollection(Arrays.asList(PUBLIC_MATCHERS));
    }
}
