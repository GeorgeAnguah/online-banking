package com.onlinebanking.shared.util;

import com.onlinebanking.constant.ProfileTypeConstants;
import com.onlinebanking.enums.ErrorMessage;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;

/**
 * This utility class holds custom operations on security used in the application.
 *
 * @author Stephen on 08/12/2021
 * @version 1.0
 * @since 1.0
 */
public final class SecurityUtils {

    /**
     * If we are running with dev profile, disable csrf and frame options to enable h2 to work.
     *
     * @param http the http request
     */
    public static void configureDevEnvironmentAccess(HttpSecurity http, Environment environment) throws Exception {
        var activeProfiles = Arrays.asList(environment.getActiveProfiles());

        if (activeProfiles.contains(ProfileTypeConstants.DEV)) {
            http.headers().frameOptions().disable().and().csrf().disable();
        }
    }

    /**
     * Checks if the current user is authenticated successfully.
     *
     * @return true if user is authenticated else false
     */
    public static boolean isUserAuthenticated() {

        final Authentication auth = getAuthentication();
        return auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken);
    }

    /**
     * This method retrieves the authentication object from the current session.
     *
     * @return Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    private SecurityUtils() {
        throw new AssertionError(ErrorMessage.NOT_INSTANTIABLE.getErrorMsg());
    }

}
