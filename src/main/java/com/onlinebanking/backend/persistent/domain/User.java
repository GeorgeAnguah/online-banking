package com.onlinebanking.backend.persistent.domain;

import com.onlinebanking.backend.persistent.domain.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

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

    @Column(nullable = false)
    private String publicId;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "Username cannot be left blank")
    @Size(min = 3, message = "Username should be at least 3 characters")
    private String username;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "Email cannot be left blank")
    @Email(message = "A valid email format is required")
    private String email;

    @ToString.Exclude
    @NotBlank(message = "Password cannot be left blank")
    @Size(min = 4, max = 15, message = "Password must be at least 4 and at most 15 characters")
    private String password;

    private String firstName;
    private String lastName;

    private String phone;
    private boolean enabled;
    private String verificationToken;

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
}
