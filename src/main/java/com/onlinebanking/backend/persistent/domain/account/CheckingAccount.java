package com.onlinebanking.backend.persistent.domain.account;

import com.onlinebanking.backend.persistent.domain.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * The CheckingAccount provides a users primary balance in the application.
 *
 * @author Matthew Puentes on 8/18/2021
 * @version 1.0
 * @since 1.0
 */
@Entity
@Getter
@Setter
@ToString
public class CheckingAccount extends BaseEntity  implements Serializable {
    private static final long serialVersionUID = 8693929414833339020L;

    @Column(unique = true, nullable = false)
    private int accountNumber;

    private BigDecimal checkingBalance;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CheckingAccount) || !super.equals(o)) {
            return false;
        }
        var checkingAccount = (CheckingAccount) o;
        return Objects.equals(getAccountNumber(), checkingAccount.getAccountNumber())
               && Objects.equals(getCheckingBalance(), checkingAccount.getCheckingBalance());
    }

    @Override
    protected boolean canEqual(Object other) {
        return other instanceof CheckingAccount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getAccountNumber(), getCheckingBalance());
    }
}
