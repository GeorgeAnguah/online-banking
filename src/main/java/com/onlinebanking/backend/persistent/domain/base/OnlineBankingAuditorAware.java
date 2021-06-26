package com.onlinebanking.backend.persistent.domain.base;

import lombok.EqualsAndHashCode;
import org.springframework.data.domain.AuditorAware;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;
import java.util.Optional;

/**
 * This class gets the application's current auditor which is the username of the authenticated user.
 *
 * @author Eric Opoku
 * @version 1.0
 * @since 1.0
 */
@EqualsAndHashCode
public final class OnlineBankingAuditorAware implements AuditorAware<String> {

    private static final String CURRENT_AUDITOR = "system";

    /**
     * Returns the current auditor of the application.
     *
     * @return the current auditor
     */
    @NonNull
    @Override
    public Optional<String> getCurrentAuditor() {

        // Check if there is a user logged in.
        // If so, use the logged in user as the current auditor.
        // spring injects an anonymousUser if there is no
        // authentication and authorization
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.nonNull(authentication)
                && authentication.isAuthenticated()
                && !(authentication instanceof AnonymousAuthenticationToken)) {
            return Optional.ofNullable(authentication.getName());
        }
        // If there is no authentication,
        // then the system will be used as the current auditor.
        return Optional.of(CURRENT_AUDITOR);
    }
}
