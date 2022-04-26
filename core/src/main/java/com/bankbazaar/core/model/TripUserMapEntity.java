package com.bankbazaar.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name="trip_user_mapping")
@IdClass(TripUserCompositeKey.class)
public class TripUserMapEntity implements Serializable,Comparable<TripUserMapEntity> {
    @Id
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="trip_id", referencedColumnName = "trip_id", nullable = false)
    private TripEntity tripId;

    @Id
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="user_id", referencedColumnName = "user_id", nullable = false)
    private UserEntity userId;

    @Column(name="user_role",nullable = false)
    private UserRole userRole;

    @CreatedDate
    @Column(name = "created_date",updatable = false)
    private Date createdTime;

    @LastModifiedDate
    @Column(name = "last_modified_date",updatable = false)
    private Date lastModifiedTime;

    @Override
    public int compareTo(TripUserMapEntity tripUserMap) {
        return getCreatedTime().compareTo(tripUserMap.getCreatedTime());
    }


}
