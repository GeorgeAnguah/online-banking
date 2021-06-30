package com.onlinebanking.backend.persistent.domain;

import com.onlinebanking.backend.persistent.domain.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

/**
 * The user role model for the application.
 *
 * @author Matthew Puentes
 * @version 1.0
 * @since 1.0
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
public class UserRole extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 2803657434288286128L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "role_id")
    private Role role;

    /**
     * Constructor for UserRole.
     *
     * @param user user for object instantiation.
     * @param role user for object instantiation.
     */
    public UserRole(User user, Role role) {
        this.user = user;
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserRole) || !super.equals(o)) {
            return false;
        }
        UserRole userRole = (UserRole) o;
        return Objects.equals(getUser(), userRole.getUser())
               && Objects.equals(getRole(), userRole.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getUser(), getRole());
    }

    @Override
    protected boolean canEqual(Object other) {
        return other instanceof UserRole;
    }
}
