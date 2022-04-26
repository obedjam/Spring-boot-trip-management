package com.bankbazaar.service.controller;

import com.bankbazaar.core.manager.TripActivityEntityManager;
import com.bankbazaar.core.manager.TripUserMapEntityManager;
import com.bankbazaar.core.model.TripActivityEntity;
import com.bankbazaar.core.model.TripUserMapEntity;
import com.bankbazaar.dto.model.TripActivityDto;
import com.bankbazaar.service.manager.DataManager;
import com.bankbazaar.service.mapper.TripActivityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/trip-activity")
public class TripActivityController {
    @Autowired
    private TripActivityEntityManager manager;

    @Autowired
    private TripUserMapEntityManager tripUserMap;

    @Autowired
    private TripActivityMapper modelMapper;

    @Autowired
    private DataManager service;

    @RequestMapping(value = "/create",method = RequestMethod.GET)
    public ModelAndView createActivity(@RequestParam Long tripId){
        ModelAndView model = new ModelAndView("create_activity");
        model.addObject("tripId", tripId);
        return model;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addTrip(@RequestParam Long tripId, @ModelAttribute TripActivityDto tripActivity, Principal principal) {
        tripActivity.setTripId(tripId);
        tripActivity.setAddedBy(service.userDetails(principal).get().getUserId());
        tripActivity.setActivityStatus("PENDING");
        manager.saveTripActivity(modelMapper.dtoToDomain(tripActivity));
        return "redirect:/trip-activity?tripId="+tripId;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView findTripById(@RequestParam Long tripId,Principal principal) {

        List<TripActivityEntity> activityList = manager.getActivityTripId(tripId);
        ModelAndView model = new ModelAndView("trip_activity");
        model.addObject("activityList", activityList);
        Optional<TripUserMapEntity> userData = tripUserMap.exists(tripId,service.userDetails(principal).get().getUserId());
        model.addObject("role",userData.get().getUserRole().toString());
        return model;
    }

    @RequestMapping(value="/update",method = RequestMethod.POST)
    public String updateTrip(@RequestParam Long activityId, @RequestParam Long tripId, @RequestParam String status) {

        TripActivityDto tripActivity = new TripActivityDto();
        tripActivity.setActivityId(activityId);
        tripActivity.setTripId(tripId);
        tripActivity.setActivityStatus(status);
        manager.updateTripActivity(modelMapper.dtoToDomain(tripActivity));

        return "redirect:/trip-activity?tripId="+tripId;
    }
}
