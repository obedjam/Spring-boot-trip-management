package com.bankbazaar.tripmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="trip")
public class Trip {
    @Id
    @Column(name="trip_id")
    @NotNull(message = "trip_id cannot be null")
    private Long tripId;

    @Column(name="trip_name")
    @NotBlank(message = "tripName cannot be blank")
    private String tripName;

    @Column(name="destination")
    @NotBlank(message = "destination cannot be blank")
    private String destination;

    @Column(name="trip_description")
    @NotBlank(message = "tripDescription cannot be blank")
    private String tripDescription;

    @Column(name="start_date")
    @NotNull(message = "startDate cannot be null")
    private Date startDate;

    @Column(name="end_date")
    @NotNull(message = "startDate cannot be null")
    private Date endDate;
}
