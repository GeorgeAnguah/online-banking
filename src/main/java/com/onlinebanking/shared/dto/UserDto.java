package com.onlinebanking.shared.dto;

import com.onlinebanking.backend.persistent.domain.UserHistory;
import com.onlinebanking.backend.persistent.domain.UserRole;
import com.onlinebanking.backend.persistent.domain.account.CheckingAccount;
import com.onlinebanking.backend.persistent.domain.account.SavingsAccount;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
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

    private CheckingAccount checkingAccount;
    private SavingsAccount savingsAccount;

    @ToString.Exclude
    private Set<UserRole> userRoles = new HashSet<>();

    @ToString.Exclude
    private Set<UserHistory> userHistories = new HashSet<>();

    public Set<UserRole> getUserRoles() {
        return new HashSet<>(userRoles);
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        if (Objects.nonNull(userRoles)) {
            this.userRoles = new HashSet<>(userRoles);
        }
    }

    public Set<UserHistory> getUserHistories() {
        return new HashSet<>(userHistories);
    }

    public void setUserHistories(Set<UserHistory> userHistories) {
        if (Objects.nonNull(userHistories)) {
            this.userHistories = new HashSet<>(userHistories);
        }
    }

    public CheckingAccount getCheckingAccount() {
        return SerializationUtils.clone(checkingAccount);
    }

    public void setCheckingAccount(CheckingAccount checkingAccount) {
        if (Objects.nonNull(checkingAccount)) {
            this.checkingAccount = SerializationUtils.clone(checkingAccount);
        }
    }

    public SavingsAccount getSavingsAccount() {
        return SerializationUtils.clone(savingsAccount);
    }

    public void setSavingsAccount(SavingsAccount savingsAccount) {
        if (Objects.nonNull(savingsAccount)) {
            this.savingsAccount = SerializationUtils.clone(savingsAccount);
        }
    }
}
