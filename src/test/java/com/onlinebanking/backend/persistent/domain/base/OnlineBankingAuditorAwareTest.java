package com.onlinebanking.backend.persistent.domain.base;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;
import java.util.Optional;

class OnlineBankingAuditorAwareTest {

    private static final String SYSTEM_USER = "system";
    private static final String ROLE_USER = "ROLE_USER";
    private static final String ANONYMOUS_USER = "anonymousUser";
    private static final String ANONYMOUS_ROLE = "ROLE_ANONYMOUS";

    @BeforeEach
    void setUp() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void getCurrentAuditor() {
        Assertions.assertEquals(SYSTEM_USER, getAuditor());
    }

    @Test
    void getCurrentAuditorWithNoAuthentication() {
        setAuthentication(ANONYMOUS_ROLE, ANONYMOUS_USER);
        SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
        Assertions.assertEquals(SYSTEM_USER, getAuditor());
    }

    @Test
    void getCurrentAuditorWithAnonymousUser() {
        setAuthentication(ANONYMOUS_ROLE, ANONYMOUS_USER);
        Assertions.assertEquals(SYSTEM_USER, getAuditor());
    }

    @Test
    void getCurrentAuditorWithAuthenticatedUser(TestInfo testInfo) {
        setAuthentication(ROLE_USER, testInfo.getDisplayName());
        Assertions.assertEquals(getAuditor(), testInfo.getDisplayName());
    }

    private String getAuditor() {
        var onlineBankingAuditorAware = new OnlineBankingAuditorAware();
        Optional<String> currentAuditor = onlineBankingAuditorAware.getCurrentAuditor();
        return currentAuditor.orElse(null);
    }

    @Test
    void equalsContract() {
        EqualsVerifier.forClass(OnlineBankingAuditorAware.class)
                .verify();
    }

    private void setAuthentication(String role, String username) {
        var authorities = Collections.singletonList(new SimpleGrantedAuthority(role));
        Authentication auth;
        if (username.equals(ANONYMOUS_USER)) {
            var user =  User.builder().username(username).password(username).authorities(authorities).build();
            auth = new AnonymousAuthenticationToken(username, user, authorities);
        } else {
            auth = new UsernamePasswordAuthenticationToken(username, null, authorities);
        }
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

}