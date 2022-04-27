package com.bankbazaar.service.manager;

import com.bankbazaar.core.manager.TripActivityManager;
import com.bankbazaar.core.model.TripActivityEntity;
import com.bankbazaar.dto.model.TripActivityDto;
import com.bankbazaar.service.mapper.TripActivityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
public class TripActivityService {
    @Autowired
    private TripActivityMapper modelMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private TripActivityManager manager;


    public TripActivityDto addTrip(Long tripId, TripActivityDto tripActivity, Principal principal) {
        tripActivity.setTripId(tripId);
        tripActivity.setAddedBy(userService.userDetails(principal).get().getUserId());
        tripActivity.setActivityStatus("PENDING");
        TripActivityEntity response = manager.saveTripActivity(modelMapper.dtoToDomain(tripActivity));
        return  modelMapper.domainToDto(response);
    }

    public List<TripActivityDto> findTripById(Long tripId,Principal principal) {

        List<TripActivityEntity> activityList = manager.getActivityTripId(tripId);
        List<TripActivityDto> activityListDto = new ArrayList<>();

        for(TripActivityEntity tripActivity : activityList)
        {
            activityListDto.add(modelMapper.domainToDto(tripActivity));
        }
        return activityListDto;
    }

    public TripActivityDto updateTrip(Long activityId, Long tripId, String status) {

        TripActivityDto tripActivity = new TripActivityDto();
        tripActivity.setActivityId(activityId);
        tripActivity.setTripId(tripId);
        tripActivity.setActivityStatus(status);
        TripActivityEntity response = manager.updateTripActivity(modelMapper.dtoToDomain(tripActivity));
        return modelMapper.domainToDto(response);
    }
}
