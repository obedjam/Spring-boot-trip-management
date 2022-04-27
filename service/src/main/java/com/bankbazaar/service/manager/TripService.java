package com.bankbazaar.service.manager;

import com.bankbazaar.core.manager.TripActivityManager;
import com.bankbazaar.core.manager.TripManager;
import com.bankbazaar.core.manager.TripUserMapManager;
import com.bankbazaar.core.model.TripActivityEntity;
import com.bankbazaar.core.model.TripEntity;
import com.bankbazaar.core.model.TripUserMapEntity;
import com.bankbazaar.core.model.UserEntity;
import com.bankbazaar.dto.model.TripDto;
import com.bankbazaar.dto.model.TripUserMapDto;
import com.bankbazaar.service.mapper.TripMapper;
import com.bankbazaar.service.mapper.TripUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TripService {
    @Autowired
    private TripManager tripManager;
    @Autowired
    private TripMapper modelMapper;
    @Autowired
    private TripUserMapper tripUserMapper;
    @Autowired
    private TripManager manager;
    @Autowired
    private TripUserMapManager tripUserMap;
    @Autowired
    private TripActivityManager tripActivity;
    @Autowired
    private UserService userService;
    @Autowired
    private TripUserMapManager tripUserMapManager;


    public Optional<TripEntity> getTripDetails(Long tripId)
    {
        return tripManager.getTripById(tripId);

    }

    public TripDto addTripService(TripDto trip, Principal principal) {


        TripEntity response = manager.saveTrip(modelMapper.dtoToDomain(trip));
        addAdmin(response, principal);

        return  modelMapper.domainToDto(response);
    }

    public ModelAndView viewTripService(Principal principal){
        UserEntity userData = userService.userDetails(principal).get();
        List<TripUserMapEntity> tripList = tripUserMap.getTripsUserId(userData.getUserId());
        List<Integer> userCount = new ArrayList<>();
        List<Integer> activityCount = new ArrayList<>();
        for (TripUserMapEntity trip : tripList)
        {
            List<TripUserMapEntity> members = tripUserMap.getTripsTripId(trip.getTripId());
            userCount.add(members.size());
            List<TripActivityEntity> activities = tripActivity.getActivityTripId(trip.getTripId());
            activityCount.add(activities.size());
        }
        ModelAndView model = new ModelAndView("trip");
        model.addObject("tripList", tripList);
        model.addObject("userCount", userCount);
        model.addObject("activityCount", activityCount);
        return model;
    }

    public void addAdmin(TripEntity trip, Principal principal)
    {
        TripUserMapDto tripUserMapping = new TripUserMapDto();
        tripUserMapping.setUserId(userService.userDetails(principal).get().getUserId());
        tripUserMapping.setTripId(trip.getTripId());
        tripUserMapping.setUserRole("ADMIN");
        tripUserMapManager.saveTripUserMapping(tripUserMapper.dtoToDomain(tripUserMapping));

    }
}
