package com.onlinebanking.config.security;

import com.onlinebanking.constant.HomeConstants;
import com.onlinebanking.constant.ProfileTypeConstants;
import com.onlinebanking.constant.SecurityConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Arrays;

/**
 * This class holds security configuration settings from this application.
 *
 * @author George on 6/19/2021
 * @version 1.0
 * @since 1.0
 */
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    private final Environment environment;
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

        // if we are running with dev profile, disable csrf and frame options to enable h2 to work.
        configureDevEnvironmentAccess(http);

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

    /**
     * If we are running with dev profile, disable csrf and frame options to enable h2 to work.
     *
     * @param http the http request
     */
    private void configureDevEnvironmentAccess(HttpSecurity http) throws Exception {
        var activeProfiles = Arrays.asList(environment.getActiveProfiles());

        if (activeProfiles.contains(ProfileTypeConstants.DEV)) {
            http.headers().frameOptions().disable().and().csrf().disable();
        }
    }
}
