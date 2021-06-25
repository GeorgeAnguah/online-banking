package com.onlinebanking.backend.persistent.domain.base;

import lombok.Getter;
import lombok.Setter;
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
import javax.persistence.SequenceGenerator;
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
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @Id
    @SequenceGenerator(name = "online-banking_sequenceGenerator", sequenceName = "online-banking_sequence")
    @GeneratedValue(generator = "online-banking_sequenceGenerator")
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
     * @return the equality of both objects.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!canEqual(o)) {
            return false;
        }
        BaseEntity that = (BaseEntity) o;
        return version == that.version;
    }

    /**
     * Called by equals method to maintain strict equality.
     * Should be overridden by subclasses.
     *
     * @param other the other object use in equality test.
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
        return Objects.hash(version);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BaseEntity{");
        sb.append(getClass().getSimpleName())
                .append(" : version=")
                .append(version)
                .append('}');
        return sb.toString();
    }
}
