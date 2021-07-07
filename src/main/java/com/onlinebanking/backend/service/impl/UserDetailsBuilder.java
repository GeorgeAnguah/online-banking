package com.onlinebanking.backend.service.impl;

import com.onlinebanking.backend.persistent.domain.User;
import com.onlinebanking.backend.persistent.domain.UserRole;
import com.onlinebanking.shared.util.validation.InputValidationUtils;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * UserDetailsBuilder builds the userDetails to be used by the application security context.
 *
 * @author George on 7/4/2021
 * @version 1.0
 * @since 1.0
 */
@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserDetailsBuilder implements UserDetails {
    private static final long serialVersionUID = -8755873164632782035L;

    private Long id;
    @EqualsAndHashCode.Include
    private String email;
    @EqualsAndHashCode.Include
    private String publicId;
    @EqualsAndHashCode.Include
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String phone;
    private boolean enabled;

    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Builds userDetails object from the specified user.
     *
     * @param user the user
     *
     * @return the userDetails
     */
    public static UserDetailsBuilder buildUserDetails(User user) {
        InputValidationUtils.validateInputs("UserDetails cannot be built from a null input", user, user, user);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (UserRole userRole : user.getUserRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(userRole.getRole().getName()));
        }
        return UserDetailsBuilder.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .password(user.getPassword())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .enabled(user.isEnabled())
                .authorities(grantedAuthorities)
                .build();
    }
}
