package com.onlinebanking.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * This class holds security configuration settings from this application.
 *
 * @author Nana on 6/19/2021
 * @version 1.0
 * @since 1.0
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static String[] publicMatchers = {
            "/"
    };

    /**
     * @param http the {@link HttpSecurity} to modify
     * @throws Exception throw when an error happens.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().
                antMatchers(publicMatchers).
                permitAll().
                and()
                .authorizeRequests().
                anyRequest().authenticated();
    }
}
