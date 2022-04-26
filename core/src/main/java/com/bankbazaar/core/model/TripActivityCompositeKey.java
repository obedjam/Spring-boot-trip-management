package com.bankbazaar.core.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripActivityCompositeKey implements Serializable {
    private Long tripId;
    private Long activityId;
}
