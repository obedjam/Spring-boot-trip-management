package com.bankbazaar.tripmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name="trip_activity")
public class TripActivity implements Serializable {
    @Id
    @Column(name="trip_id",nullable = false)
    private Long id;

    @MapsId
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="trip_id", referencedColumnName = "trip_id", nullable = false, insertable = false, updatable = false)
    private Trip tripId;

    @Column(name="activity_description",nullable = false)
    private String activityDescription;

    @Column(name="location",nullable = false)
    private String location;

    @Column(name="activity_time",nullable = false)
    private LocalDate activityTime;

    @Column(name="added_by",nullable = false)
    private Long addedBy;

    @Column(name="activity_status",nullable = false)
    private Integer activityStatus;

    @CreatedDate
    @Column(name = "created_date")
    private LocalDate createdTime;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    private LocalDate lastModifiedTime;

}
