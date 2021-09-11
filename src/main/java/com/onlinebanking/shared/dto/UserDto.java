package com.onlinebanking.shared.dto;

import com.onlinebanking.backend.persistent.domain.UserHistory;
import com.onlinebanking.backend.persistent.domain.UserRole;
import com.onlinebanking.backend.persistent.domain.account.CheckingAccount;
import com.onlinebanking.backend.persistent.domain.account.SavingsAccount;
import com.onlinebanking.enums.ErrorMessage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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
    @NotBlank(message = ErrorMessage.BLANK_USERNAME)
    private String username;

    @ToString.Exclude
    @NotBlank(message = ErrorMessage.BLANK_PASSWORD)
    private String password;
    private String firstName;
    private String lastName;

    @EqualsAndHashCode.Include
    @NotBlank(message = ErrorMessage.BLANK_EMAIL)
    @Email(message = ErrorMessage.INVALID_EMAIL)
    private String email;
    private String phone;
    private boolean enabled;
    private String verificationToken;

    private CheckingAccount checkingAccount;
    private SavingsAccount savingsAccount;

    @ToString.Exclude
    private Set<UserRole> userRoles = new HashSet<>();

    @ToString.Exclude
    private Set<UserHistory> userHistories = new HashSet<>();
}
