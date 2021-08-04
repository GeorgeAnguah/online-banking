package com.onlinebanking.shared.util;

import com.onlinebanking.constant.ProfileTypeConstants;
import com.onlinebanking.enums.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;
import java.util.Objects;

/**
 * This utility class holds custom operations on security used in the application.
 *
 * @author Eric Opoku
 * @version 1.0
 * @since 1.0
 */
@Slf4j
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
     * Returns true if the user is authenticated.
     *
     * @return if user is authenticated
     */
    public static boolean isUserAuthenticated() {
        Authentication authentication = getAuthentication();
        return Objects.nonNull(authentication)
               && authentication.isAuthenticated()
               && !(authentication instanceof AnonymousAuthenticationToken);
    }

    /**
     * Retrieve the authentication object from the current session.
     *
     * @return authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    private SecurityUtils() {
        throw new AssertionError(ErrorMessage.NOT_INSTANTIABLE.getErrorMsg());
    }
}
