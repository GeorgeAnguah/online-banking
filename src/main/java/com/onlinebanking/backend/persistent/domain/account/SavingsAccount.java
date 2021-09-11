package com.onlinebanking.backend.persistent.domain.account;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

/**
 * The SavingsAccount class provides a users savings balance in the application.
 *
 * @author Matthew Puentes on 8/22/2021
 * @version 1.0
 * @since 1.0
 */
@Entity
@Getter
@Setter
@ToString
public class SavingsAccount extends BaseAccount implements Serializable {
    private static final long serialVersionUID = -3497998863675085296L;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SavingsAccount) || !super.equals(o)) {
            return false;
        }
        return super.equals(o);
    }

    @Override
    protected boolean canEqual(Object other) {
        return other instanceof SavingsAccount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode());
    }
}
