package com.bankbazaar.tripmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name="trip_user_mapping")
public class TripUserMapping implements Serializable {
    @Id
    @Column(name="trip_id",nullable = false)
    private Long tripId;

    @Id
    @Column(name="user_id",nullable = false)
    private Long userId;

    @Column(name="user_role",nullable = false)
    private String userRole;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="trip_id", referencedColumnName = "trip_id")
    private Trip trip;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="user_id", referencedColumnName = "user_id")
    private Users user;

    @CreatedDate
    @Column(name = "created_date")
    private Date createdTime;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    private Date lastModifiedTime;
}
