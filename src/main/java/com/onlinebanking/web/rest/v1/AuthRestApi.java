package com.onlinebanking.web.rest.v1;

import com.onlinebanking.backend.service.UserService;
import com.onlinebanking.backend.service.security.EncryptionService;
import com.onlinebanking.constant.SecurityConstants;
import com.onlinebanking.web.payload.request.LoginRequest;
import com.onlinebanking.web.payload.response.LoginResponse;
import com.onlinebanking.web.payload.response.LogoutResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * This class attempt to authenticate with AuthenticationManager bean, add authentication object to
 * SecurityContextHolder then Generate JWT token, then return JWT to client.
 *
 * @author Eric Opoku
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(SecurityConstants.API_AUTH_ROOT_URL)
public class AuthRestApi {

    private final UserService userService;
    private final EncryptionService encryptionService;
    private final AuthenticationManager authenticationManager;

    /**
     * Attempts to authenticate with the provided credentials.
     *
     * @param loginRequest the login request
     *
     * @return the jwt token details
     */
    @PostMapping(SecurityConstants.LOGIN)
    public ResponseEntity<LoginResponse> authenticateUser(
            @CookieValue(required = false) String accessToken,
            @CookieValue(required = false) String refreshToken,
            @Valid @RequestBody LoginRequest loginRequest) {

        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String decryptedAccessToken = encryptionService.decrypt(accessToken);
        String decryptedRefreshToken = encryptionService.decrypt(refreshToken);

        return userService.login(loginRequest.getUsername(), decryptedAccessToken, decryptedRefreshToken);
    }

    /**
     * Attempts to refresh token.
     *
     * @return the jwt token details
     */
    @PostMapping(SecurityConstants.REFRESH)
    public ResponseEntity<LoginResponse> refreshToken(
            @CookieValue(required = false) String accessToken,
            @CookieValue(required = false) String refreshToken) {

        String decryptedAccessToken = encryptionService.decrypt(accessToken);
        String decryptedRefreshToken = encryptionService.decrypt(refreshToken);

        return userService.refresh(decryptedAccessToken, decryptedRefreshToken);
    }

    /**
     * Initiates the logout routine for the request submitted.
     *
     * @param request  the request
     * @param response the response
     *
     * @return response entity
     */
    @PostMapping(SecurityConstants.LOGOUT)
    public ResponseEntity<LogoutResponse> logout(HttpServletRequest request, HttpServletResponse response) {
        return userService.logout(request, response);
    }

}
