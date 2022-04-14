package com.bankbazaar.tripmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="trip_user_mapping")
public class TripUserMapping {
    @Id
    @Column(name="trip_id")
    @NotNull(message = "trip_id cannot be null")
    private Long tripId;

    @Id
    @Column(name="user_id")
    @NotNull(message = "user_id cannot be null")
    private Long userId;

    @Column(name="user_role")
    @NotBlank(message = "user_role cannot be blank")
    private String userRole;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="trip_id", referencedColumnName = "trip_id")
    private Trip trip;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="user_id", referencedColumnName = "user_id")
    private Users user;
}
