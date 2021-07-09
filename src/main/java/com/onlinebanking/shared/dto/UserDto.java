package com.onlinebanking.shared.dto;

import com.onlinebanking.backend.persistent.domain.UserHistory;
import com.onlinebanking.backend.persistent.domain.UserRole;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * The UserDto transfers user details from outside into the application and vice versa.
 *
 * @author Eric Opoku
 * @version 1.0
 * @since 1.0
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class UserDto extends BaseDto implements Serializable {
    private static final long serialVersionUID = -6342630857637389028L;

    @EqualsAndHashCode.Include
    private String publicId;

    @EqualsAndHashCode.Include
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    @EqualsAndHashCode.Include
    private String email;
    private String phone;
    private boolean enabled;
    private String verificationToken;

    @ToString.Exclude
    private Set<UserRole> userRoles = new HashSet<>();

    @ToString.Exclude
    private Set<UserHistory> userHistories = new HashSet<>();
}
