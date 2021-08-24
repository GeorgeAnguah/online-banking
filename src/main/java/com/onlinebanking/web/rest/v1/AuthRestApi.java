package com.onlinebanking.web.rest.v1;

import com.onlinebanking.backend.service.UserService;
import com.onlinebanking.backend.service.security.CookieService;
import com.onlinebanking.backend.service.security.EncryptionService;
import com.onlinebanking.backend.service.security.JwtService;
import com.onlinebanking.constant.SecurityConstants;
import com.onlinebanking.enums.ErrorMessage;
import com.onlinebanking.enums.OperationStatus;
import com.onlinebanking.enums.TokenType;
import com.onlinebanking.shared.util.SecurityUtils;
import com.onlinebanking.web.payload.request.LoginRequest;
import com.onlinebanking.web.payload.response.JwtResponseBuilder;
import com.onlinebanking.web.payload.response.LogoutResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.Duration;
import java.util.Date;

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
@RequestMapping(SecurityConstants.API_V1_AUTH_ROOT_URL)
public class AuthRestApi {

    private static final int NUMBER_OF_MINUTES_TO_EXPIRE = 30;

    private final JwtService jwtService;
    private final UserService userService;
    private final CookieService cookieService;
    private final EncryptionService encryptionService;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;

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
    @CrossOrigin(origins = "http://localhost:3000/")
    @GetMapping(SecurityConstants.REFRESH_TOKEN)
    public ResponseEntity<JwtResponseBuilder> refreshToken(@CookieValue String refreshToken) {
        String decryptedRefreshToken = encryptionService.decrypt(refreshToken);
        boolean refreshTokenValid = jwtService.isValidJwtToken(decryptedRefreshToken);

        if (!refreshTokenValid) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_REFRESH_TOKEN.getErrorMsg());
        }
        String username = jwtService.getUsernameFromToken(decryptedRefreshToken);
        var userDetails = userDetailsService.loadUserByUsername(username);
        SecurityUtils.authenticateUser(userDetails);

        Date expiration = DateUtils.addMinutes(new Date(), NUMBER_OF_MINUTES_TO_EXPIRE);
        String newAccessToken = jwtService.generateJwtToken(username, expiration);
        String encryptedAccessToken = encryptionService.encrypt(newAccessToken);

        return ResponseEntity
                .ok(JwtResponseBuilder.buildJwtResponse(encryptedAccessToken));
    }

    /**
     * Logout the user from the system and clear all cookies from request and response.
     *
     * @param request  the request
     * @param response the response
     *
     * @return response entity
     */
    @DeleteMapping(SecurityConstants.LOGOUT)
    public ResponseEntity<LogoutResponse> logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityUtils.logout(request, response);

        var responseHeaders = cookieService.addDeletedCookieToHeaders(TokenType.REFRESH);
        var logoutResponse = new LogoutResponse(OperationStatus.SUCCESS);

        return ResponseEntity.ok().headers(responseHeaders).body(logoutResponse);
    }

    /**
     * Updates the accessToken and refreshToken accordingly.
     *
     * @param username       the username
     * @param isAccessValid  if the access token is valid
     * @param isRefreshValid if the refresh token is valid
     * @param headers        the accessToken
     */
    private String updateCookies(String username, boolean isAccessValid, boolean isRefreshValid, HttpHeaders headers) {
        boolean generateNewAccessToken = false;
        if (!isAccessValid && !isRefreshValid || isAccessValid && isRefreshValid) {
            generateNewAccessToken = true;
            String newRefreshToken = jwtService.generateJwtToken(username);

            Duration duration = Duration.ofDays(SecurityConstants.REFRESH_TOKEN_DURATION);
            cookieService.addCookieToHeaders(headers, TokenType.REFRESH, newRefreshToken, duration);
        }
        if (!isAccessValid && isRefreshValid) {
            generateNewAccessToken = true;
        }

        Date expiration = DateUtils.addMinutes(new Date(), NUMBER_OF_MINUTES_TO_EXPIRE);
        return generateNewAccessToken ? jwtService.generateJwtToken(username, expiration) : StringUtils.EMPTY;
    }

}
