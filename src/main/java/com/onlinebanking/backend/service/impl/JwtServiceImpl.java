package com.onlinebanking.backend.service.impl;

import com.onlinebanking.backend.service.JwtService;
import com.onlinebanking.shared.util.validation.InputValidationUtils;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * This is the implementation of the jwt service.
 *
 * @author Eric Opoku
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@Service
public class JwtServiceImpl implements JwtService {

    private static final String TOKEN_CREATED_SUCCESS = "Token successfully created as {}";

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private int jwtExpiration;


    /**
     * Generate a JwtToken for the specified username.
     *
     * @param username the username
     *
     * @return the token
     */
    @Override
    public String generateJwtToken(String username) {
        InputValidationUtils.validateInputs(getClass(), username);
        return generateJwtToken(username, new Date(System.currentTimeMillis() + jwtExpiration));
    }

    /**
     * Generate a JwtToken for the specified username.
     *
     * @param username   the username
     * @param expiration the expiration date
     *
     * @return the token
     */
    @Override
    public String generateJwtToken(String username, Date expiration) {
        InputValidationUtils.validateInputs(getClass(), username, expiration);
        var jwtToken = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();

        LOG.debug(TOKEN_CREATED_SUCCESS, jwtToken);
        return jwtToken;
    }

    /**
     * Retrieve username from the token.
     *
     * @param token the token
     *
     * @return the username
     */
    @Override
    public String getUsernameFromToken(String token) {
        InputValidationUtils.validateInputs(getClass(), token);
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    /**
     * Validates the Jwt token passed to it.
     *
     * @param token the token
     *
     * @return if valid or not
     */
    @Override
    public boolean isValidJwtToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            LOG.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            LOG.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            LOG.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            LOG.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            LOG.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
}
