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
@Table(name="trip_activity")
public class TripActivity implements Serializable {
    @Id
    @Column(name="trip_id",nullable = false)
    private Long tripId;

    @Column(name="activity_description",nullable = false)
    private String activityDescription;

    @Column(name="location",nullable = false)
    private String location;

    @Column(name="activity_time",nullable = false)
    private Date activityTime;

    @Column(name="added_by",nullable = false)
    private Long addedBy;

    @Column(name="activity_status",nullable = false)
    private Integer activityStatus;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="trip_id", referencedColumnName = "trip_id")
    private Trip trip;

    @CreatedDate
    @Column(name = "created_date")
    private Date createdTime;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    private Date lastModifiedTime;

}
