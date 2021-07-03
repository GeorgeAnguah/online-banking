package com.onlinebanking.backend.persistent.domain;

import com.onlinebanking.backend.persistent.domain.base.BaseEntity;
import com.onlinebanking.enums.UserHistoryType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

/**
 * Class UserHistory captures activities happening to user such as profile update, password reset etc.
 *
 * @author George on 7/2/2021
 * @version 1.0
 * @since 1.0
 */
@Entity
@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class UserHistory extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -418682848586685969L;

    @Column(unique = true, nullable = false)
    private final String publicId;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    private final User user;

    @Enumerated(EnumType.STRING)
    private final UserHistoryType userHistoryType;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserHistory)) {
            return false;
        }
        if (!(super.equals(o))) {
            return false;
        }
        UserHistory that = (UserHistory) o;
        if (!that.canEqual(this)) {
            return false;
        }
        return
                Objects.equals(publicId, that.publicId)
                && Objects.equals(getUser(), that.getUser())
                && Objects.equals(getUserHistoryType(), that.getUserHistoryType());

    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getPublicId(), getUser(), getUserHistoryType());
    }

    @Override
    protected boolean canEqual(Object other) {
        return other instanceof UserHistory;
    }

}
