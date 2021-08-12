package com.onlinebanking.web.rest.v1;

import com.onlinebanking.backend.service.JwtService;
import com.onlinebanking.backend.service.UserService;
import com.onlinebanking.backend.service.security.EncryptionService;
import com.onlinebanking.constant.SecurityConstants;
import com.onlinebanking.enums.ErrorMessage;
import com.onlinebanking.enums.OperationStatus;
import com.onlinebanking.enums.TokenType;
import com.onlinebanking.shared.util.CookieUtils;
import com.onlinebanking.shared.util.SecurityUtils;
import com.onlinebanking.web.payload.request.LoginRequest;
import com.onlinebanking.web.payload.response.JwtResponseBuilder;
import com.onlinebanking.web.payload.response.LogoutResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.Duration;

/**
 * This class attempt to authenticate with AuthenticationManager bean, add authentication object to
 * SecurityContextHolder then Generate JWT token, then return JWT to client.
 *
 * @author Eric Opoku
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(SecurityConstants.API_AUTH_ROOT_URL)
public class AuthRestApi {

    private final JwtService jwtService;
    private final UserService userService;
    private final EncryptionService encryptionService;
    private final AuthenticationManager authenticationManager;

    private static final int DURATION = 7;

    /**
     * Attempts to authenticate with the provided credentials.
     *
     * @param loginRequest the login request
     *
     * @return the jwt token details
     */
    @PostMapping(SecurityConstants.LOGIN)
    public ResponseEntity<JwtResponseBuilder> authenticateUser(
            @CookieValue(required = false) String accessToken,
            @CookieValue(required = false) String refreshToken,
            @Valid @RequestBody LoginRequest loginRequest) {

        String username = loginRequest.getUsername();
        SecurityUtils.authenticateUser(authenticationManager, username, loginRequest.getPassword());
        if (Boolean.FALSE.equals(userService.existsByUsername(username))) {
            LOG.warn("No record found for storedUser with username {}", username);
            throw new UsernameNotFoundException("User with username " + username + " not found");
        }
        String decryptedAccessToken = encryptionService.decrypt(accessToken);
        String decryptedRefreshToken = encryptionService.decrypt(refreshToken);

        boolean accessTokenValid = jwtService.isValidJwtToken(decryptedAccessToken);
        boolean refreshTokenValid = jwtService.isValidJwtToken(decryptedRefreshToken);

        HttpHeaders responseHeaders = new HttpHeaders();
        String newAccessToken = updateCookies(username, accessTokenValid, refreshTokenValid, responseHeaders);
        String encryptedAccessToken = encryptionService.encrypt(newAccessToken);

        return ResponseEntity
                .ok()
                .headers(responseHeaders)
                .body(JwtResponseBuilder.buildJwtResponse(encryptedAccessToken));
    }

    /**
     * Refreshes the current access token and refresh token accordingly.
     *
     * @return the jwt token details
     */
    @PostMapping(SecurityConstants.REFRESH)
    public ResponseEntity<String> refreshToken(@CookieValue String refreshToken) {
        String decryptedRefreshToken = encryptionService.decrypt(refreshToken);
        boolean refreshTokenValid = jwtService.isValidJwtToken(decryptedRefreshToken);

        if (!refreshTokenValid) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_REFRESH_TOKEN.getErrorMsg());
        }

        String username = jwtService.getUsernameFromToken(decryptedRefreshToken);
        String newAccessToken = jwtService.generateJwtToken(username);
        String encryptedAccessToken = encryptionService.encrypt(newAccessToken);

        return ResponseEntity.ok(encryptedAccessToken);
    }

    /**
     * Logout the user from the system and clear all cookies from request and response.
     *
     * @param request  the request
     * @param response the response
     *
     * @return response entity
     */
    @PostMapping(SecurityConstants.LOGOUT)
    public ResponseEntity<LogoutResponse> logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityUtils.logout(request, response);

        HttpHeaders responseHeaders = new HttpHeaders();
        CookieUtils.deleteCookie(responseHeaders, TokenType.ACCESS);
        CookieUtils.deleteCookie(responseHeaders, TokenType.REFRESH);

        LogoutResponse logoutResponse = new LogoutResponse(OperationStatus.SUCCESS);
        return ResponseEntity.ok().headers(responseHeaders).body(logoutResponse);
    }

    /**
     * Updates the accessToken and refreshToken accordingly.
     *
     * @param username          the username
     * @param isAccessValid  if the access token is valid
     * @param isRefreshValid if the refresh token is valid
     * @param headers   the accessToken
     */
    private String updateCookies(String username, boolean isAccessValid, boolean isRefreshValid, HttpHeaders headers) {
        boolean generateNewAccessToken = false;
        if (!isAccessValid && !isRefreshValid || isAccessValid && isRefreshValid) {
            generateNewAccessToken = true;
            String newRefreshToken = jwtService.generateJwtToken(username);

            CookieUtils.addCookieToHeaders(headers, TokenType.REFRESH, newRefreshToken, Duration.ofDays(DURATION));
        }
        if (!isAccessValid && isRefreshValid) {
            generateNewAccessToken = true;
        }
        return generateNewAccessToken ? jwtService.generateJwtToken(username) : StringUtils.EMPTY;
    }

}
