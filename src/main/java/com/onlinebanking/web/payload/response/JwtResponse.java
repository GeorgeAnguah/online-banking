package com.onlinebanking.web.payload.response;

import com.onlinebanking.backend.service.impl.UserDetailsBuilder;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class describes the Jwt response object.
 *
 * @author George on 8/9/2021
 * @version 1.0
 * @since 1.0
 */
@Data
@Builder
public class JwtResponse implements Serializable {
    private static final long serialVersionUID = -3625429150594757621L;

    private String accessToken;
    private String type;
    private String publicId;
    private String username;
    private String email;

    @EqualsAndHashCode.Exclude
    private List<String> roles;

    /**
     * Build jwtResponse object from the specified userDetails.
     *
     * @param jwToken     the jwToken.
     * @param userDetails the userDetails.
     *
     * @return the jwtResponse object.
     */
    public static JwtResponse buildJwtResponse(String jwToken, UserDetailsBuilder userDetails) {
        List<String> roleList = new ArrayList<>();
        for (GrantedAuthority authority : userDetails.getAuthorities()) {
            roleList.add(authority.getAuthority());
        }
        return JwtResponse.builder()
                .accessToken(jwToken)
                .email(userDetails.getEmail())
                .username(userDetails.getUsername())
                .publicId(userDetails.getPublicId())
                .type("Bearer")
                .roles(roleList)
                .build();
    }
}

