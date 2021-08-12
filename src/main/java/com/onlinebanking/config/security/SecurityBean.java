package com.onlinebanking.config.security;

import com.onlinebanking.constant.SecurityConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;
import java.time.Duration;
import java.util.List;

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

    /**
     * Making use of persistent option instead of in-memory for maximum security.
     *
     * @return persistentTokenRepository
     */
    @Bean
    public PersistentTokenRepository persistentRepository(DataSource dataSource) {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);

        return jdbcTokenRepository;
    }

    /**
     * Configures cors for all requests towards the API.
     *
     * @return CorsConfigurationSource
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource(@Value("${cors.allowed-origins}") List<String> origins) {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setMaxAge(Duration.ofHours(1));
        corsConfiguration.setAllowedOrigins(origins);
        corsConfiguration.setAllowedHeaders(SecurityConstants.ALLOWED_HTTP_HEADERS);
        corsConfiguration.setAllowedMethods(SecurityConstants.ALLOWED_HTTP_METHODS);
        corsConfiguration.setExposedHeaders(List.of(HttpHeaders.AUTHORIZATION, HttpHeaders.SET_COOKIE));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration(SecurityConstants.API_ROOT_URL_MAPPING, corsConfiguration);
        return source;
    }
}
