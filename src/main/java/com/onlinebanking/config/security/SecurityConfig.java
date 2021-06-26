package com.onlinebanking.config.security;

import com.onlinebanking.constant.HomeConstants;
import com.onlinebanking.constant.SecurityConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

/**
 * This class holds security configuration settings from this application.
 *
 * @author George on 6/19/2021
 * @version 1.0
 * @since 1.0
 */
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

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
                .antMatchers(SecurityConstants.getPublicMatchers().toArray(new String[0])).permitAll()
                .and()
                .authorizeRequests()
                .anyRequest().authenticated();
        http.formLogin()
                .failureUrl(SecurityConstants.LOGIN_ERROR)
                .loginPage(HomeConstants.INDEX_URL_MAPPING).permitAll();
        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher(SecurityConstants.LOGOUT))
                .logoutSuccessUrl(SecurityConstants.LOGIN_LOGOUT)
                .deleteCookies(SecurityConstants.REMEMBER_ME).permitAll();
        http.rememberMe();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
}
