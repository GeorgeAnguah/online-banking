package com.onlinebanking.web.payload.response;

import com.onlinebanking.backend.service.impl.UserDetailsBuilder;
import com.onlinebanking.constant.SecurityConstants;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class models the format of the jwt response produced.
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
     * @param userDetails the userDetails
     *
     * @return the jwtResponse
     */
    public static JwtResponseBuilder buildJwtResponse(String jwtToken, UserDetailsBuilder userDetails) {
        var roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return JwtResponseBuilder.builder()
                        .type(SecurityConstants.BEARER)
                        .accessToken(jwtToken)
                        .publicId(userDetails.getPublicId())
                        .username(userDetails.getUsername())
                        .email(userDetails.getEmail())
                        .roles(roles)
                        .build();
    }
}
