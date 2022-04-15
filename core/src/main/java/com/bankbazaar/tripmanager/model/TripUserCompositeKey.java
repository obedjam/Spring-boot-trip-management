package com.bankbazaar.tripmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripUserCompositeKey implements Serializable {
    private Long trip_id;
    private Long user_id;
}
