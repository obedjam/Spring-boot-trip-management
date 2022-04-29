package com.bankbazaar.service.controller;

import com.bankbazaar.core.manager.TripUserMapManager;
import com.bankbazaar.core.security.LoginUserDetails;
import com.bankbazaar.dto.model.TripActivityDto;
import com.bankbazaar.service.service.TripActivityService;
import com.bankbazaar.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

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
    public String addTrip(@RequestParam Long tripId, @ModelAttribute TripActivityDto tripActivity, @AuthenticationPrincipal LoginUserDetails loginUserDetails) {
        tripActivityService.addTripActivity(tripId, tripActivity, loginUserDetails.getUserId());
        return "redirect:/trip-activity?tripId="+tripId;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView findTripById(@RequestParam Long tripId,@AuthenticationPrincipal LoginUserDetails loginUserDetails) {
        ModelAndView model = new ModelAndView("trip_activity");
        model.addObject("activityList", tripActivityService.findTripActivityById(tripId,loginUserDetails.getUserId()));
        model.addObject("role",tripActivityService.getUserRole(tripId,loginUserDetails.getUserId()));
        return model;
    }

    @RequestMapping(value="/update",method = RequestMethod.POST)
    public String updateTrip(@RequestParam Long activityId, @RequestParam Long tripId, @RequestParam String status) {
        tripActivityService.updateTripActivity(activityId, tripId, status);
        return "redirect:/trip-activity?tripId="+tripId;
    }
}
