package com.onlinebanking.backend.service.impl;

import com.onlinebanking.backend.persistent.domain.User;
import com.onlinebanking.backend.persistent.domain.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * UserDetailsBuilder builds the userDetails to be used by the application security context.
 *
 * @author George on 7/4/2021
 * @version 1.0
 * @since 1.0
 */
public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = -8755873164632782035L;
    private final User user;

    /**
     * Creates a UserDetails with this user.
     *
     * @param user user needed to create UserDetails Object.
     */
    public UserDetailsImpl(User user) {
        this.user = user;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

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

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<String> authorities = new ArrayList<>();
        for (UserRole userRole : user.getUserRoles()) {
            authorities.add(userRole.getRole().getName());
        }
        return AuthorityUtils.createAuthorityList(authorities.toArray(new String[0]));
    }
}
