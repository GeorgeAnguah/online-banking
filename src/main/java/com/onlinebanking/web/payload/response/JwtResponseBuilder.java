package com.onlinebanking.web.payload.response;

import com.onlinebanking.backend.service.impl.UserDetailsBuilder;
import com.onlinebanking.constant.SecurityConstants;
import com.onlinebanking.shared.util.SecurityUtils;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class models the format of the login response produced.
 *
 * @author George on 8/9/2021
 * @version 1.0
 * @since 1.0
 */
@Data
@Builder
public class JwtResponseBuilder implements Serializable {
    private static final long serialVersionUID = -3625429150594757621L;

    private String accessToken;
    private String type;
    private String publicId;
    private String username;
    private String email;

    @EqualsAndHashCode.Exclude
    private List<String> roles;

    /**
     * Builds jwtResponseBuilder object from the specified userDetails.
     *
     * @param jwtToken the jwtToken
     *
     * @return the jwtResponse
     */
    public static JwtResponseBuilder buildJwtResponse(final String jwtToken) {
        return buildJwtResponse(jwtToken, null);
    }

    /**
     * Build jwtResponse object from the specified userDetails.
     *
     * @param jwToken     the jwToken.
     * @param userDetails the userDetails.
     *
     * @return the jwtResponse object.
     */
    public static JwtResponseBuilder buildJwtResponse(String jwToken, UserDetailsBuilder userDetails) {
        var localUserDetails = userDetails;
        if (Objects.isNull(localUserDetails)) {
            localUserDetails = SecurityUtils.getAuthenticatedUserDetails();
        }

        if (Objects.nonNull(localUserDetails)) {
            List<String> roleList = new ArrayList<>();
            for (GrantedAuthority authority : localUserDetails.getAuthorities()) {
                roleList.add(authority.getAuthority());
            }
            return JwtResponseBuilder.builder()
                    .accessToken(jwToken)
                    .email(localUserDetails.getEmail())
                    .username(localUserDetails.getUsername())
                    .publicId(localUserDetails.getPublicId())
                    .type(SecurityConstants.BEARER)
                    .roles(roleList)
                    .build();
        }
        return JwtResponseBuilder.builder().build();
    }
}

