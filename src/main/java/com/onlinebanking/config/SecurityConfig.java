package com.onlinebanking.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * This class holds security configuration settings from this application.
 *
 * @author Nana on 6/19/2021
 * @version 1.0
 * @since 1.0
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static String[] publicMatchers = {
            "/"
    };

    /**
     * Override this method to configure the {@link HttpSecurity}.
     * Typically subclasses should not call super as it may override their
     * configuration.
     *
     * @param http the {@link HttpSecurity} to modify.
     * @throws Exception thrown when error happens during authentication.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(publicMatchers).permitAll()
                .and()
                .authorizeRequests()
                .anyRequest().authenticated();
    }
}
