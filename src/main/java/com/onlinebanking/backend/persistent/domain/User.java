package com.onlinebanking.backend.persistent.domain;

import com.onlinebanking.backend.persistent.domain.account.CheckingAccount;
import com.onlinebanking.backend.persistent.domain.account.SavingsAccount;
import com.onlinebanking.backend.persistent.domain.base.BaseEntity;
import com.onlinebanking.enums.ErrorMessage;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.SerializationUtils;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * The user model for the application.
 *
 * @author Eric Opoku
 * @version 1.0
 * @since 1.0
 */
@Entity
@Getter
@Setter
@ToString
public class User extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 7538542321562810251L;

    @Column(unique = true, nullable = false)
    private String publicId;

    @Column(unique = true, nullable = false)
    @NotBlank(message = ErrorMessage.BLANK_USERNAME)
    @Size(min = 3, max = 50, message = ErrorMessage.USERNAME_SIZE)
    private String username;

    @Column(unique = true, nullable = false)
    @NotBlank(message = ErrorMessage.BLANK_EMAIL)
    @Email(message = ErrorMessage.INVALID_EMAIL)
    private String email;

    @ToString.Exclude
    @NotBlank(message = ErrorMessage.BLANK_PASSWORD)
    private String password;

    private String firstName;
    private String lastName;
    private String phone;
    private String verificationToken;

    private boolean enabled;

    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserRole> userRoles = new HashSet<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserHistory> userHistories = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    private CheckingAccount checkingAccount;

    @OneToOne(cascade = CascadeType.ALL)
    private SavingsAccount savingsAccount;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User) || !super.equals(o)) {
            return false;
        }
        var user = (User) o;
        return Objects.equals(getPublicId(), user.getPublicId())
               && Objects.equals(getUsername(), user.getUsername())
               && Objects.equals(getEmail(), user.getEmail());

    }

    @Override
    protected boolean canEqual(Object other) {
        return other instanceof User;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getPublicId(), getUsername(), getEmail());
    }

    /**
     * Add userRole to this User.
     *
     * @param userRole userRole to be added if not already present.
     */
    public void addUserRole(UserRole userRole) {
        userRoles.add(userRole);
        userRole.setUser(this);
    }

    /**
     * Remove userRole from this User.
     *
     * @param userRole userRole to be removed if present.
     */
    public void removeUserRole(UserRole userRole) {
        userRoles.remove(userRole);
        userRole.setUser(null);
    }

    /**
     * Add a UserHistory to this user.
     *
     * @param userHistory userHistory to be added.
     */
    public void addUserHistory(UserHistory userHistory) {
        userHistories.add(userHistory);
        userHistory.setUser(this);
    }

}
