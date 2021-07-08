package com.onlinebanking.backend.persistent.domain;

import com.onlinebanking.enums.RoleType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * Role to be assigned to users.
 *
 * @author George on 6/26/2021
 * @version 1.0
 * @since 1.0
 */
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Role implements Serializable {
    private static final long serialVersionUID = 7008351760784988067L;

    @Id
    private int id;
    private String name;

    /**
     * The Role class creates a role for the user.
     *
     * @param roleType assigns the role properties.
     */
    public Role(RoleType roleType) {
        this.id = roleType.getId();
        this.name = roleType.name();
    }

    /**
     * Evaluate the equality of Role class.
     *
     * @param o is the other object use in equality test.
     *
     * @return the equality of both objects.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Role)) {
            return false;
        }
        var that = (Role) o;
        return Objects.equals(name, that.name);
    }

    /**
     * Hashcode of Role base on name.
     *
     * @return the hash value.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
