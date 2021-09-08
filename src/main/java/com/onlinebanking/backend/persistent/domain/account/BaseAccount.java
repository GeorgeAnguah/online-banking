package com.onlinebanking.backend.persistent.domain.account;

import com.onlinebanking.backend.persistent.domain.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.MappedSuperclass;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * The BaseAccount provides shared details between users different accounts in the application.
 *
 * @author Matthew Puentes on 8/18/2021
 * @version 1.0
 * @since 1.0
 */
@Getter
@Setter
@ToString
@MappedSuperclass
public class BaseAccount extends BaseEntity {

    private Long accountNumber;
    private BigDecimal balance;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BaseAccount) || !super.equals(o)) {
            return false;
        }
        var checkingAccount = (BaseAccount) o;
        return Objects.equals(getAccountNumber(), checkingAccount.getAccountNumber())
               && Objects.equals(getBalance(), checkingAccount.getBalance());
    }

    @Override
    protected boolean canEqual(Object other) {
        return other instanceof BaseAccount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getAccountNumber(), getBalance());
    }
}
