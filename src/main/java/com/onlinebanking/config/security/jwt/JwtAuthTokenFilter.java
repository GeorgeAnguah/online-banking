package com.onlinebanking.config.security.jwt;

import com.onlinebanking.backend.service.JwtService;
import com.onlinebanking.backend.service.security.EncryptionService;
import com.onlinebanking.enums.TokenType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * This is a filter base class that is used to guarantee a single execution per request dispatch.
 *
 * @author Eric Opoku
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@RequiredArgsConstructor
public class JwtAuthTokenFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final EncryptionService encryptionService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        var jwt = getJwtToken(request);
        if (StringUtils.isNotBlank(jwt) && jwtService.isValidJwtToken(jwt)) {
            var username = jwtService.getUsernameFromToken(jwt);
            var userDetails = userDetailsService.loadUserByUsername(username);
            var authorities = userDetails.getAuthorities();
            var authentication = new UsernamePasswordAuthenticationToken(userDetails, null, authorities);

            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    /**
     * Retrieves the jwt token from the request cookie if present and valid.
     *
     * @param request the httpRequest
     * @return the jwt token
     */
    private String getJwtToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (Objects.nonNull(cookies)) {
            for (Cookie cookie : cookies) {
                if (TokenType.ACCESS.getName().equals(cookie.getName())) {
                    String accessToken = cookie.getValue();
                    if (accessToken == null) {
                        return null;
                    }

                    return encryptionService.decrypt(accessToken);
                }
            }
        }
        return null;
    }
}
