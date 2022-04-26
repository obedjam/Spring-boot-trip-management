package com.bankbazaar.service.manager;

import com.bankbazaar.core.manager.TripActivityManager;
import com.bankbazaar.core.manager.TripUserMapManager;
import com.bankbazaar.core.model.TripActivityEntity;
import com.bankbazaar.core.model.TripUserMapEntity;
import com.bankbazaar.dto.model.TripActivityDto;
import com.bankbazaar.service.mapper.TripActivityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

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
    public ModelAndView createActivityService(Long tripId){
        ModelAndView model = new ModelAndView("create_activity");
        model.addObject("tripId", tripId);
        return model;
    }

    public String addTripService(Long tripId, TripActivityDto tripActivity, Principal principal) {
        tripActivity.setTripId(tripId);
        tripActivity.setAddedBy(userService.userDetails(principal).get().getUserId());
        tripActivity.setActivityStatus("PENDING");
        manager.saveTripActivity(modelMapper.dtoToDomain(tripActivity));
        return "redirect:/trip-activity?tripId="+tripId;
    }

    public ModelAndView findTripByIdService(Long tripId,Principal principal) {

        List<TripActivityEntity> activityList = manager.getActivityTripId(tripId);
        ModelAndView model = new ModelAndView("trip_activity");
        model.addObject("activityList", activityList);
        Optional<TripUserMapEntity> userData = tripUserMap.exists(tripId,userService.userDetails(principal).get().getUserId());
        model.addObject("role",userData.get().getUserRole().toString());
        return model;
    }

    public String updateTripService(Long activityId, Long tripId, String status) {

        TripActivityDto tripActivity = new TripActivityDto();
        tripActivity.setActivityId(activityId);
        tripActivity.setTripId(tripId);
        tripActivity.setActivityStatus(status);
        manager.updateTripActivity(modelMapper.dtoToDomain(tripActivity));

        return "redirect:/trip-activity?tripId="+tripId;
    }
}
