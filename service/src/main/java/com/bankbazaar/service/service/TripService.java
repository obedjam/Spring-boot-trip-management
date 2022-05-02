package com.bankbazaar.service.service;

import com.bankbazaar.core.manager.TripManager;
import com.bankbazaar.core.manager.TripUserMapManager;
import com.bankbazaar.core.model.*;
import com.bankbazaar.dto.model.TripActivityDto;
import com.bankbazaar.dto.model.TripDto;
import com.bankbazaar.dto.model.UserDto;
import com.bankbazaar.service.mapper.TripMapper;
import com.bankbazaar.service.mapper.TripUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TripService {
    @Autowired
    private TripMapper modelMapper;
    @Autowired
    private TripUserMapper tripUserMapper;
    @Autowired
    private TripManager manager;
    @Autowired
    private TripUserMapManager tripUserMap;
    @Autowired
    private TripActivityService tripActivity;
    @Autowired
    private UserService userService;
    @Autowired
    private TripUserMapManager tripUserMapManager;


    public TripDto getTripDetails(Long tripId)
    {
        Optional<TripEntity> response = manager.getTripById(tripId);
        if(response.isPresent()) {
            return  modelMapper.domainToDto(response.get());
        }
        return  null;
    }

    public TripDto addTrip(TripDto trip, Long userId) {


        TripEntity response = manager.saveTrip(modelMapper.dtoToDomain(trip));
        addAdmin(response, userId);

        return  modelMapper.domainToDto(response);
    }

    public ModelAndView viewTrip(Long userId){
        UserDto userData = userService.loggedInUserDetails(userId);
        List<TripUserMapEntity> tripList = tripUserMap.getTripsUserId(userData.getUserId());
        List<Integer> userCount = new ArrayList<>();
        List<Integer> activityCount = new ArrayList<>();
        for (TripUserMapEntity trip : tripList)
        {
            List<TripUserMapEntity> members = tripUserMap.getTripsTripId(trip.getTripId());
            userCount.add(members.size());
            List<TripActivityDto> activities = tripActivity.findTripActivityById(trip.getTripId(),userId);
            activityCount.add(activities.size());
        }
        ModelAndView model = new ModelAndView("trip");
        model.addObject("tripList", tripList);
        model.addObject("userCount", userCount);
        model.addObject("activityCount", activityCount);
        return model;
    }

    public void addAdmin(TripEntity trip, Long userId)
    {
        TripUserMapEntity tripUserMapping = new TripUserMapEntity();
        tripUserMapping.setUserId(userId);
        tripUserMapping.setTripId(trip.getTripId());
        tripUserMapping.setUserRole(UserRole.ADMIN);
        tripUserMapManager.saveTripUserMapping(tripUserMapping);

    }
}
