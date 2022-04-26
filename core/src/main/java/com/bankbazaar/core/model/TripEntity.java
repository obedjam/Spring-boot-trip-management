package com.bankbazaar.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name="trip")
@NoArgsConstructor
public class TripEntity implements Serializable {
    @Id
    @GeneratedValue
    @Column(name="trip_id", nullable = false)
    private Long tripId;

    @Column(name="trip_name",nullable = false)
    private String tripName;

    @Column(name="destination",nullable = false)
    private String destination;

    @Column(name="trip_description",nullable = false)
    private String tripDescription;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="start_date",nullable = false)
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="end_date",nullable = false)
    private Date endDate;

    @CreatedDate
    @Column(name = "created_date",updatable = false)
    private Date createdTime;

    @LastModifiedDate
    @Column(name = "last_modified_date",updatable = false)
    private Date lastModifiedTime;

}
