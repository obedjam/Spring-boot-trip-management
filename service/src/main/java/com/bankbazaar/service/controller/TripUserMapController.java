package com.bankbazaar.service.controller;


import com.bankbazaar.core.model.TripUserMapEntity;
import com.bankbazaar.service.manager.TripUserMapService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Slf4j
@Controller
@RequestMapping("/trip-user-mapping")
public class TripUserMapController {

    @Autowired
    private TripUserMapService tripUserMapService;


    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getTrips( @RequestParam Long tripId, Principal principal) {
        return tripUserMapService.getTripsService(tripId,principal);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> addUsers( @RequestParam Long tripId,@RequestParam Long userId) {
        return tripUserMapService.addUsersService(tripId, userId);
    }

    @RequestMapping(value="/delete",method = RequestMethod.POST)
    public String deleteUsers(@RequestParam Long tripId, @RequestParam Long userId) {
        return  tripUserMapService.deleteUsersService(tripId,userId);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<TripUserMapEntity> updateUsers( @RequestParam Long tripId, @RequestParam Long userId, @RequestParam String role) {
        return tripUserMapService.updateUsersService(tripId,userId,role);
    }
}
