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
@Table(name="trip_activity")
@NoArgsConstructor
public class TripActivityEntity implements Serializable {

    @Id
    @GeneratedValue
    @Column(name="activity_id",nullable = false)
    private Long activityId;

    @Column(name = "trip_id", nullable = false )
    private Long tripId;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="trip_id", referencedColumnName = "trip_id", insertable = false, updatable = false)
    private TripEntity trip;

    @Column(name="activity_description",nullable = false)
    private String activityDescription;

    @Column(name="location",nullable = false)
    private String location;

    @Column(name="activity_time",nullable = false)
    private Date activityTime;

    @Column(name="added_by",nullable = false)
    private Long addedBy;

    @Column(name="activity_status",nullable = false)
    private ActivityStatus activityStatus;

    @CreatedDate
    @Column(name = "created_date",updatable = false)
    private Date createdTime;

    @LastModifiedDate
    @Column(name = "last_modified_date",updatable = false)
    private Date lastModifiedTime;

}
