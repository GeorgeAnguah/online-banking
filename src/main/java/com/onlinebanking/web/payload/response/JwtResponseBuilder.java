package com.onlinebanking.web.payload.response;

import com.onlinebanking.backend.service.impl.UserDetailsBuilder;
import com.onlinebanking.constant.SecurityConstants;
import com.onlinebanking.shared.util.SecurityUtils;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * This class models the format of the login response produced.
 *
 * @author Eric Opoku
 * @version 1.0
 * @since 1.0
 */
@Data
@Builder
public class JwtResponseBuilder {
    private String accessToken;
    private String type;
    private String publicId;
    private String username;
    private String email;
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
     * Builds jwtResponseBuilder object from the specified userDetails.
     *
     * @param jwtToken    the jwtToken
     * @param userDetails the userDetails
     *
     * @return the jwtResponse
     */
    public static JwtResponseBuilder buildJwtResponse(final String jwtToken, final UserDetailsBuilder userDetails) {
        var localUserDetails = userDetails;
        if (Objects.isNull(localUserDetails)) {
            localUserDetails = SecurityUtils.getAuthenticatedUserDetails();
        }

        if (Objects.nonNull(localUserDetails)) {
            var roles = localUserDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            return JwtResponseBuilder.builder()
                    .type(SecurityConstants.BEARER)
                    .accessToken(jwtToken)
                    .publicId(localUserDetails.getPublicId())
                    .username(localUserDetails.getUsername())
                    .email(localUserDetails.getEmail())
                    .roles(roles)
                    .build();
        }
        return builder().build();
    }
}
