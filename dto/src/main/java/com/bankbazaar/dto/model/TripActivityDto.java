package com.bankbazaar.dto.model;
import com.bankbazaar.core.model.ActivityStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripActivityDto {

    private Long activityId;
    private Long tripId;
    private String activityDescription;
    private String location;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date activityTime;
    private Long addedBy;
    private String activityStatus;
}
