package com.onlinebanking.backend.persistent.domain;

import com.onlinebanking.backend.persistent.domain.base.BaseEntity;
import com.onlinebanking.enums.UserHistoryType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Objects;

/**
 * Class UserHistory captures activities happening to user such as profile update, password reset etc.
 *
 * @author George on 7/2/2021
 * @version 1.0
 * @since 1.0
 */
@Entity
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Getter
public class UserHistory extends BaseEntity {

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private final User user;

    @Enumerated(EnumType.STRING)
    private final UserHistoryType userHistoryType;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserHistory) || !super.equals(o)) {
            return false;
        }
        UserHistory that = (UserHistory) o;
        if (!that.canEqual(o)) {
            return false;
        }
        return Objects.equals(user, that.user)
               && Objects.equals(userHistoryType, that.userHistoryType)
               && getCreatedAt().isEqual(that.getCreatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), user, userHistoryType, getCreatedAt());
    }

    @Override
    protected boolean canEqual(Object other) {
        return other instanceof UserHistory;
    }

}
