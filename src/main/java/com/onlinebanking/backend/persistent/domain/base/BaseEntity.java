package com.onlinebanking.backend.persistent.domain.base;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * BaseEntity holds information common to all entities in this application.
 * All application entities should extend this class.
 *
 * @author George on 6/24/2021
 * @version 1.0
 * @since 1.0
 */
@Getter
@Setter
@ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @Id
    @GenericGenerator(
            name = "onlineBankingSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            })
    @GeneratedValue(generator = "onlineBankingSequenceGenerator")
    private Long id;

    @Version
    private int version;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @CreatedBy
    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Column(name = "updated_by")
    @LastModifiedBy
    private String updatedBy;

    /**
     * Evaluate the equality of BaseEntity class.
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
        if (!(o instanceof BaseEntity)) {
            return false;
        }
        BaseEntity that = (BaseEntity) o;
        if (!that.canEqual(this)) {
            return false;
        }
        return getVersion() == that.getVersion();
    }

    /**
     * Called by equals method to maintain strict equality.
     * Should be overridden by subclasses.
     *
     * @param other the other object use in equality test.
     *
     * @return true for objects derived from similar class, otherwise false.
     */
    protected boolean canEqual(Object other) {
        return other instanceof BaseEntity;
    }

    /**
     * Hashcode of BaseEntity base on version.
     *
     * @return the hash value.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getVersion());
    }
}
