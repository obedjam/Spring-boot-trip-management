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
@EntityListeners(AuditingEntityListener.class)
@Table(name="trip_user_mapping")
@IdClass(TripUserCompositeKey.class)
@NoArgsConstructor
public class TripUserMapEntity implements Serializable,Comparable<TripUserMapEntity> {
    @Id
    @Column(name = "trip_id", nullable = false )
    private Long tripId;

    @Id
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="trip_id", referencedColumnName = "trip_id", insertable = false, updatable = false)
    private TripEntity trip;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private UserEntity user;

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
