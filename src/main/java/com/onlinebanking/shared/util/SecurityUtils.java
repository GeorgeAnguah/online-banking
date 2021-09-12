package com.onlinebanking.shared.util;

import com.onlinebanking.backend.service.UserService;
import com.onlinebanking.backend.service.impl.UserDetailsBuilder;
import com.onlinebanking.constant.ProfileTypeConstants;
import com.onlinebanking.enums.ErrorMessage;
import com.onlinebanking.shared.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Objects;

/**
 * This utility class holds custom operations on security used in the application.
 *
 * @author Stephen on 08/12/2021
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
        var profiles = Arrays.asList(environment.getActiveProfiles());

        if (profiles.contains(ProfileTypeConstants.DEV) || profiles.contains(ProfileTypeConstants.DEV_DOCKER)) {
            http.headers().frameOptions().sameOrigin().and().csrf().disable().cors();
        }
    }

    /**
     * Returns true if the user is authenticated.
     *
     * @return if user is authenticated
     */
    public static boolean isAuthenticated(Authentication authentication) {
        return Objects.nonNull(authentication)
               && authentication.isAuthenticated()
               && !(authentication instanceof AnonymousAuthenticationToken);
    }

    /**
     * Returns true if the user is authenticated.
     *
     * @return if user is authenticated
     */
    public static boolean isAuthenticated() {
        return isAuthenticated(getAuthentication());
    }

    /**
     * Retrieve the authentication object from the current session.
     *
     * @return authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * Sets the provided authentication object to the SecurityContextHolder.
     *
     * @param authentication the authentication
     */
    public static void setAuthentication(Authentication authentication) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    /**
     * Clears the securityContextHolder.
     *
     */
    public static void clearAuthentication() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    /**
     * Authenticate the user with the provided username programmatically.
     *
     * @param userService the user service
     * @param username    the username
     */
    public static void authenticateUser(final UserService userService, final String username) {
        var userDetails = userService.getUserDetails(username);
        if (Objects.nonNull(userDetails)) {
            authenticateUser(userDetails);
        }
    }

    /**
     * Creates an authentication object with the userDetails then set authentication to SecurityContextHolder.
     * @param userDetails the userDetails
     */
    public static void authenticateUser(UserDetails userDetails) {
        var auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        setAuthentication(auth);
    }

    /**
     * Creates an authentication object with the credentials then set authentication to SecurityContextHolder.
     *
     * @param authenticationManager the authentication manager
     * @param username              the username
     * @param password              the password
     */
    public static void authenticateUser(AuthenticationManager authenticationManager, String username, String password) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        var authentication = authenticationManager.authenticate(authenticationToken);

        setAuthentication(authentication);
    }

    /**
     * Returns the user details from the authenticated object if authenticated.
     *
     * @return the user details
     */
    public static UserDetailsBuilder getAuthenticatedUserDetails() {
        if (isAuthenticated()) {
            return (UserDetailsBuilder) getAuthentication().getPrincipal();
        }
        return null;
    }

    /**
     * Retrieve the authenticated user from the current session.
     *
     * @return the userDetailsBuilder
     */
    public static UserDto getAuthorizedUserDto() {
        return UserUtils.convertToUserDto(getAuthorizedUserDetails());
    }

    /**
     * Retrieve the authenticated user from the current session.
     *
     * @return the userDetailsBuilder
     */
    public static UserDetailsBuilder getAuthorizedUserDetails() {
        var userDetails = getAuthenticatedUserDetails();
        if (Objects.isNull(userDetails)) {
            LOG.warn(ErrorMessage.UNAUTHORIZED_ACCESS_MESSAGE.getErrorMsg());
            return null;
        }
        return userDetails;
    }

    /**
     * Logout the user from the system and clear all cookies from request and response.
     *
     * @param request  the request
     * @param response the response
     */
    public static void logout(HttpServletRequest request, HttpServletResponse response) {

        String rememberMeCookieKey = AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY;
        CookieClearingLogoutHandler logoutHandler = new CookieClearingLogoutHandler(rememberMeCookieKey);

        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, response, null);
        securityContextLogoutHandler.logout(request, response, null);
    }

    private SecurityUtils() {
        throw new AssertionError(ErrorMessage.NOT_INSTANTIABLE.getErrorMsg());
    }
}
