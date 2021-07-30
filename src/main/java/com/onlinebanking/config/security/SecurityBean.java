package com.onlinebanking.config.security;

import com.onlinebanking.constant.SecurityConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * This class defines the beans needed for the security operation of the application.
 *
 * @author Matthew Puentes on 6/26/2021
 * @version 1.0
 * @since 1.0
 */

@Configuration
@RequiredArgsConstructor
public class SecurityBean {

    /**
     * PasswordEncoder bean used in security operations.
     *
     * @return BcryptPasswordEncoder with security strength 12
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(SecurityConstants.SECURITY_STRENGTH);
    }
}
