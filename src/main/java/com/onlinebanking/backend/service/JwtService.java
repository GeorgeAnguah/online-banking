package com.onlinebanking.backend.service;

import java.util.Date;

/**
 * This is the contract for the jwt service operations.
 *
 * @author Eric Opoku
 * @version 1.0
 * @since 1.0
 */
public interface JwtService {

    /**
     * Generate a JwtToken for the specified username.
     *
     * @param username the username
     * @return the token
     */
    String generateJwtToken(String username);

    /**
     * Generate a JwtToken for the specified username.
     *
     * @param username the username
     * @param expiration the expiration date
     * @return the token
     */
    String generateJwtToken(String username, Date expiration);

    /**
     * Retrieve username from the token.
     *
     * @param token the token
     * @return the username
     */
    String getUsernameFromToken(String token);

    /**
     * Validates the Jwt token passed to it.
     *
     * @param token the token
     * @return if valid or not
     */
    boolean isValidJwtToken(String token);
}
