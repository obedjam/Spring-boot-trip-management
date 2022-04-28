package com.bankbazaar.service.service;

import com.bankbazaar.core.manager.TripActivityManager;
import com.bankbazaar.core.manager.TripUserMapManager;
import com.bankbazaar.core.model.ActivityStatus;
import com.bankbazaar.core.model.TripActivityEntity;
import com.bankbazaar.core.model.TripUserMapEntity;
import com.bankbazaar.core.model.UserRole;
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
    @Autowired
    private TripUserMapManager tripUserMap;



    public TripActivityDto addTripActivity(Long tripId, TripActivityDto tripActivity, Principal principal) {
        tripActivity.setTripId(tripId);
        tripActivity.setAddedBy(userService.userDetails(principal).get().getUserId());
        tripActivity.setActivityStatus("PENDING");
        TripActivityEntity response = manager.saveTripActivity(modelMapper.dtoToDomain(tripActivity));
        return  modelMapper.domainToDto(response);
    }

    public List<TripActivityDto> findTripActivityById(Long tripId,Principal principal) {
        UserRole userRole = getUserRole(tripId,userService.userDetails(principal).get().getUserId());

        List<TripActivityEntity> activityList = manager.getActivityTripId(tripId);
        List<TripActivityDto> activityListDto = new ArrayList<>();
        List<TripActivityDto> approvedActivityList =new ArrayList<>();

        for(TripActivityEntity tripActivity : activityList)
        {
            activityListDto.add(modelMapper.domainToDto(tripActivity));
            if(tripActivity.getActivityStatus()==ActivityStatus.APPROVED)
            {
                approvedActivityList.add(modelMapper.domainToDto(tripActivity));
            }
        }

        if(userRole==UserRole.USER)
        {
            return approvedActivityList;
        }
        return activityListDto;
    }

    public TripActivityDto updateTripActivity(Long activityId, Long tripId, String status) {

        TripActivityDto tripActivity = new TripActivityDto();
        tripActivity.setActivityId(activityId);
        tripActivity.setTripId(tripId);
        tripActivity.setActivityStatus(status);
        TripActivityEntity response = manager.updateTripActivity(modelMapper.dtoToDomain(tripActivity));
        return modelMapper.domainToDto(response);
    }

    public UserRole getUserRole(Long tripId, Long userId)
    {
        TripUserMapEntity userData = tripUserMap.exists(tripId, userId).get();
        return userData.getUserRole();
    }
}
