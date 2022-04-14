package com.bankbazaar.tripmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Time;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="trip_activity")
public class TripActivity {
    @Id
    @Column(name="trip_id")
    @NotNull(message = "trip_id cannot be null")
    private Long tripId;

    @Column(name="activity_description")
    @NotBlank(message = "activity_description cannot be blank")
    private String activityDescription;

    @Column(name="location")
    @NotBlank(message = "location cannot be blank")
    private String location;

    @Column(name="activity_time")
    @NotNull(message = "activity_time cannot be null")
    private Time activityTime;

    @Column(name="added_by")
    @NotNull(message = "added_by cannot be null")
    private Long addedBy;

    @Column(name="activity_status")
    @NotNull(message = "activity_status cannot be null")
    private Integer activityStatus;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="trip_id", referencedColumnName = "trip_id")
    private Trip trip;
}
