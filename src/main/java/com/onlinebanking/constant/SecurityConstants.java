package com.onlinebanking.constant;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * This class holds all security-related URL mappings constants.
 *
 * @author George on 6/20/2021
 * @version 1.0
 * @since 1.0
 */
public final class SecurityConstants {

    public static final String LOGIN_LOGOUT = "/?logout";
    public static final String LOGIN_ERROR = "/?error";
    public static final String LOGOUT = "/logout";
    public static final String REMEMBER_ME = "remember-me";
    public static final int SECURITY_STRENGTH = 12;
    public static final String H2_CONSOLE_URL_MAPPING = "/console-h2/**";

    private static final String[] PUBLIC_MATCHERS = {
            HomeConstants.INDEX_URL_MAPPING,
            "/css/**",
            "/js/**",
            "/images/**",
            "/fonts/**",
            "/webjars/**",
            "/resources/**",
            "/static/**"
    };

    private SecurityConstants() {
    }

    /**
     * Public matchers to allow access to the application.
     * @return public matchers.
     */
    public static Collection<String> getPublicMatchers() {
        return Collections.unmodifiableCollection(Arrays.asList(PUBLIC_MATCHERS));
    }
}
