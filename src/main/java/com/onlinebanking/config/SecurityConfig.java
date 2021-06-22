package com.onlinebanking.config;

import com.onlinebanking.constant.HomeConstants;
import com.onlinebanking.constant.SecurityConstants;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * This class holds security configuration settings from this application.
 *
 * @author Nana on 6/19/2021
 * @version 1.0
 * @since 1.0
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] PUBLIC_MATCHERS = {
            HomeConstants.INDEX_URL_MAPPING,
            SecurityConstants.CSS,
            SecurityConstants.JS,
            SecurityConstants.IMAGES,
            SecurityConstants.FONTS,
            SecurityConstants.WEBJARS,
            SecurityConstants.RESOURCES,
            SecurityConstants.STATIC

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
                .antMatchers(PUBLIC_MATCHERS).permitAll()
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
}
