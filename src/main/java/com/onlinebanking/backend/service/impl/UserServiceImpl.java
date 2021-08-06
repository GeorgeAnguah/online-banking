package com.onlinebanking.backend.service.impl;

import com.onlinebanking.backend.persistent.domain.Role;
import com.onlinebanking.backend.persistent.domain.User;
import com.onlinebanking.backend.persistent.domain.UserHistory;
import com.onlinebanking.backend.persistent.domain.UserRole;
import com.onlinebanking.backend.persistent.repository.UserRepository;
import com.onlinebanking.backend.service.JwtService;
import com.onlinebanking.backend.service.UserService;
import com.onlinebanking.constant.UserConstants;
import com.onlinebanking.enums.OperationStatus;
import com.onlinebanking.enums.RoleType;
import com.onlinebanking.enums.TokenType;
import com.onlinebanking.enums.UserHistoryType;
import com.onlinebanking.shared.dto.UserDto;
import com.onlinebanking.shared.util.CookieUtils;
import com.onlinebanking.shared.util.SecurityUtils;
import com.onlinebanking.shared.util.StringUtils;
import com.onlinebanking.shared.util.UserUtils;
import com.onlinebanking.shared.util.validation.InputValidationUtils;
import com.onlinebanking.web.payload.response.LoginResponse;
import com.onlinebanking.web.payload.response.LogoutResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

/**
 * The UserServiceImpl class provides implementation for the UserService definitions.
 *
 * @author Eric Opoku
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private static final int DEFAULT_DURATION = 3_600_000;

    /**
     * Create the userDto with the userDto instance given.
     *
     * @param userDto the userDto with updated information
     *
     * @return the updated userDto.
     * @throws IllegalArgumentException in case the given entity is {@literal null}
     */
    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {
        InputValidationUtils.validateInputs(getClass(), userDto);
        return createUser(userDto, Collections.singleton(RoleType.ROLE_CUSTOMER));
    }

    /**
     * Create the userDto with the userDto instance given.
     *
     * @param userDto   the userDto with updated information
     * @param roleTypes the roleTypes.
     *
     * @return the updated userDto.
     * @throws IllegalArgumentException in case the given entity is {@literal null}
     */
    @Override
    @Transactional
    public UserDto createUser(UserDto userDto, Set<RoleType> roleTypes) {
        InputValidationUtils.validateInputs(getClass(), userDto, roleTypes);

        User localUser = userRepository.findByEmail(userDto.getEmail());
        if (Objects.nonNull(localUser)) {
            if (!localUser.isEnabled()) {
                LOG.debug(UserConstants.USER_EXIST_BUT_NOT_ENABLED, userDto.getEmail(), localUser);
                return UserUtils.convertToUserDto(localUser);
            }
            LOG.warn(UserConstants.USER_ALREADY_EXIST, userDto.getEmail());
        } else {
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            userDto.setPublicId(StringUtils.generatePublicId());
            return persistUser(userDto, roleTypes, UserHistoryType.CREATED);
        }
        return null;
    }

    /**
     * Returns a user for the given username or null if a user could not be found.
     *
     * @param username The username associated to the user to find
     *
     * @return a user for the given username or null if a user could not be found
     * @throws IllegalArgumentException in case the given entity is {@literal null}
     */
    @Override
    public UserDto findByUsername(String username) {
        InputValidationUtils.validateInputs(this, username);
        User storedUser = userRepository.findByUsername(username);
        if (Objects.isNull(storedUser)) {
            return null;
        }
        return UserUtils.convertToUserDto(storedUser);
    }

    /**
     * Returns a user for the given email or null if a user could not be found.
     *
     * @param email The email associated to the user to find
     *
     * @return a user for the given email or null if a user could not be found
     * @throws IllegalArgumentException in case the given entity is {@literal null}
     */
    @Override
    public UserDto findByEmail(String email) {
        InputValidationUtils.validateInputs(this, email);
        User storedUser = userRepository.findByEmail(email);
        if (Objects.isNull(storedUser)) {
            return null;
        }
        return UserUtils.convertToUserDto(storedUser);
    }

    /**
     * Validates and update the access token and refresh token accordingly.
     *
     * @param username     the username
     * @param accessToken  the accessToken
     * @param refreshToken the refreshToken
     *
     * @return the http headers
     */
    @Override
    public ResponseEntity<LoginResponse> login(String username, String accessToken, String refreshToken) {
        if (Boolean.FALSE.equals(userRepository.existsByUsernameOrderById(username))) {
            LOG.warn("No record found for storedUser with username {}", username);
            throw new UsernameNotFoundException("User with username " + username + " not found");
        }

        boolean accessTokenValid = jwtService.isValidJwtToken(accessToken);
        boolean refreshTokenValid = jwtService.isValidJwtToken(refreshToken);

        HttpHeaders responseHeaders = new HttpHeaders();
        updateCookies(username, accessTokenValid, refreshTokenValid, responseHeaders);

        String message = "Auth successful. Tokens are created in cookie.";
        LoginResponse loginResponse = new LoginResponse(message, OperationStatus.SUCCESS);
        return ResponseEntity.ok().headers(responseHeaders).body(loginResponse);
    }

    /**
     * Refreshes the current access token and refresh token accordingly.
     *
     * @param accessToken  the accessToken
     * @param refreshToken the refreshToken
     *
     * @return the http headers
     */
    @Override
    public ResponseEntity<LoginResponse> refresh(String accessToken, String refreshToken) {
        boolean refreshTokenValid = jwtService.isValidJwtToken(refreshToken);

        if (!refreshTokenValid) {
            throw new IllegalArgumentException("Refresh Token is invalid!");
        }
        String username = jwtService.getUsernameFromToken(accessToken);
        String newAccessToken = jwtService.generateJwtToken(username);

        HttpHeaders responseHeaders = new HttpHeaders();
        long duration = new Date().getTime() + DEFAULT_DURATION;
        CookieUtils.addCookieToHeaders(responseHeaders, TokenType.ACCESS, newAccessToken, duration);
        CookieUtils.deleteCookie(responseHeaders, TokenType.JSESSIONID);

        String message = "Auth successful. Tokens are created in cookie.";
        LoginResponse loginResponse = new LoginResponse(message, OperationStatus.SUCCESS);
        return ResponseEntity.ok().headers(responseHeaders).body(loginResponse);
    }

    /**
     * Logout the user from the system and clear all cookies from request and response.
     *
     * @param request  the request
     * @param response the response
     *
     * @return the http headers
     */
    @Override
    public ResponseEntity<LogoutResponse> logout(HttpServletRequest request, HttpServletResponse response) {

        SecurityUtils.logout(request, response);

        HttpHeaders responseHeaders = new HttpHeaders();
        CookieUtils.deleteCookie(responseHeaders, TokenType.ACCESS);
        CookieUtils.deleteCookie(responseHeaders, TokenType.REFRESH);
        CookieUtils.deleteCookie(responseHeaders, TokenType.JSESSIONID);

        String message = "Logout successful. Tokens are removed from cookie.";
        LogoutResponse logoutResponse = new LogoutResponse(message, OperationStatus.SUCCESS);
        return ResponseEntity.ok().headers(responseHeaders).body(logoutResponse);
    }

    /**
     * Transfers user details to a user object then persist to database.
     *
     * @param userDto         the userDto
     * @param roleTypes       the roleTypes
     * @param userHistoryType the user history type
     *
     * @return the userDto
     */
    private UserDto persistUser(UserDto userDto, Set<RoleType> roleTypes, UserHistoryType userHistoryType) {
        var user = UserUtils.convertToUser(userDto);
        for (RoleType roleType : roleTypes) {
            user.addUserRole(new UserRole(user, new Role(roleType)));
        }
        user.addUserHistory(new UserHistory(StringUtils.generatePublicId(), user, userHistoryType));

        var persistedUser = userRepository.save(user);
        LOG.debug(UserConstants.USER_CREATED_SUCCESSFULLY, persistedUser);
        return UserUtils.convertToUserDto(persistedUser);
    }

    /**
     * Updates the accessToken and refreshToken accordingly.
     *
     * @param username          the username
     * @param isAccessValid  if the access token is valid
     * @param isRefreshValid if the refresh token is valid
     * @param headers   the response headers
     */
    private void updateCookies(String username, boolean isAccessValid, boolean isRefreshValid, HttpHeaders headers) {

        String newAccessToken;
        long duration = new Date().getTime() + DEFAULT_DURATION;
        if (!isAccessValid && !isRefreshValid || isAccessValid && isRefreshValid) {
            newAccessToken = jwtService.generateJwtToken(username);
            String newRefreshToken = jwtService.generateJwtToken(username);

            CookieUtils.addCookieToHeaders(headers, TokenType.ACCESS, newAccessToken, duration);
            CookieUtils.addCookieToHeaders(headers, TokenType.REFRESH, newRefreshToken, duration);
        }

        if (!isAccessValid && isRefreshValid) {
            newAccessToken = jwtService.generateJwtToken(username);
            CookieUtils.addCookieToHeaders(headers, TokenType.ACCESS, newAccessToken, duration);
        }
        CookieUtils.deleteCookie(headers, TokenType.JSESSIONID);
    }
}
