package com.bankbazaar.service.controller;

import com.bankbazaar.core.manager.TripActivityEntityManager;
import com.bankbazaar.core.manager.TripEntityManager;
import com.bankbazaar.core.manager.TripUserMapEntityManager;
import com.bankbazaar.core.model.TripActivityEntity;
import com.bankbazaar.core.model.TripEntity;
import com.bankbazaar.core.model.TripUserMapEntity;
import com.bankbazaar.core.model.UserEntity;
import com.bankbazaar.dto.model.TripDto;
import com.bankbazaar.service.manager.DataManager;
import com.bankbazaar.service.mapper.TripMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/trips")
public class TripController {
    @Autowired
    private TripEntityManager manager;

    @Autowired
    private TripUserMapEntityManager tripUserMap;
    @Autowired
    private TripActivityEntityManager tripActivity;
    @Autowired
    private DataManager service;
    @Autowired
    private TripMapper modelMapper;

    @RequestMapping(value = "/create",method = RequestMethod.GET)
    public String createTrip(){
        return "create_trip";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addTrip( @ModelAttribute TripDto trip, Principal principal) {


        TripEntity response = manager.saveTrip(modelMapper.dtoToDomain(trip));
        service.addAdmin(response, principal);

        return "redirect:/trips";
    }
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView viewTrip(Principal principal){
        UserEntity userData = service.userDetails(principal).get();
        List<TripUserMapEntity> tripList = tripUserMap.getTripsUserId(userData.getUserId());
        List<Integer> userCount = new ArrayList<>();
        List<Integer> activityCount = new ArrayList<>();
        for (TripUserMapEntity trip : tripList)
        {
            List<TripUserMapEntity> members = tripUserMap.getTripsTripId(trip.getTripId().getTripId());
            userCount.add(members.size());
            List<TripActivityEntity> activities = tripActivity.getActivityTripId(trip.getTripId().getTripId());
            activityCount.add(activities.size());
        }
        ModelAndView model = new ModelAndView("trip");
        model.addObject("tripList", tripList);
        model.addObject("userCount", userCount);
        model.addObject("activityCount", activityCount);
        return model;
    }

}
