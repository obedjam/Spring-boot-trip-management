package com.bankbazaar.dto.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripUserMappingDto {

    private Long tripId;
    private Long userId;
    private String userRole;

}
