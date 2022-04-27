package com.bankbazaar.service.controller;

import com.bankbazaar.core.manager.TripUserMapManager;
import com.bankbazaar.core.model.TripUserMapEntity;
import com.bankbazaar.dto.model.TripActivityDto;
import com.bankbazaar.service.manager.TripActivityService;
import com.bankbazaar.service.manager.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/trip-activity")
public class TripActivityController {
    @Autowired
    private TripActivityService tripActivityService;
    @Autowired
    private TripUserMapManager tripUserMap;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/create",method = RequestMethod.GET)
    public ModelAndView createActivity(@RequestParam Long tripId){
        ModelAndView model = new ModelAndView("create_activity");
        model.addObject("tripId", tripId);
        return model;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addTrip(@RequestParam Long tripId, @ModelAttribute TripActivityDto tripActivity, Principal principal) {
        tripActivityService.addTrip(tripId, tripActivity, principal);
        return "redirect:/trip-activity?tripId="+tripId;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView findTripById(@RequestParam Long tripId,Principal principal) {
        ModelAndView model = new ModelAndView("trip_activity");
        model.addObject("activityList", tripActivityService.findTripById(tripId,principal));
        Optional<TripUserMapEntity> userData = tripUserMap.exists(tripId,userService.userDetails(principal).get().getUserId());
        model.addObject("role",userData.get().getUserRole().toString());
        return model;
    }

    @RequestMapping(value="/update",method = RequestMethod.POST)
    public String updateTrip(@RequestParam Long activityId, @RequestParam Long tripId, @RequestParam String status) {
        tripActivityService.updateTrip(activityId, tripId, status);
        return "redirect:/trip-activity?tripId="+tripId;
    }
}
