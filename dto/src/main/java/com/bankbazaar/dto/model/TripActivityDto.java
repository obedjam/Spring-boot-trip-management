package com.bankbazaar.dto.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripActivityDto {

    private Long id;
    private String activityDescription;
    private String location;
    private Date activityTime;
    private Long addedBy;
    private Integer activityStatus;
}
