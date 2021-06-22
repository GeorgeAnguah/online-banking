package com.onlinebanking.constant;

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
    public static final String WEBJARS = "/webjars/**";
    public static final String CSS = "/css/**";
    public static final String IMAGES = "/images/**";
    public static final String FONTS = "/fonts/**";
    public static final String JS = "/js/**";
    public static final String STATIC = "/static/**";
    public static final String RESOURCES = "/resources/**";

    private SecurityConstants() {
    }
}
