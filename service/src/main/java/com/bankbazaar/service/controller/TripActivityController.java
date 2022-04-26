package com.bankbazaar.service.controller;

import com.bankbazaar.dto.model.TripActivityDto;
import com.bankbazaar.service.manager.TripActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/trip-activity")
public class TripActivityController {
    @Autowired
    private TripActivityService tripActivityService;

    @RequestMapping(value = "/create",method = RequestMethod.GET)
    public ModelAndView createActivity(@RequestParam Long tripId){
        return tripActivityService.createActivityService(tripId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addTrip(@RequestParam Long tripId, @ModelAttribute TripActivityDto tripActivity, Principal principal) {
        return  tripActivityService.addTripService(tripId, tripActivity, principal);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView findTripById(@RequestParam Long tripId,Principal principal) {
        return  tripActivityService.findTripByIdService(tripId,principal);
    }

    @RequestMapping(value="/update",method = RequestMethod.POST)
    public String updateTrip(@RequestParam Long activityId, @RequestParam Long tripId, @RequestParam String status) {
        return  tripActivityService.updateTripService(activityId, tripId, status);
    }
}
