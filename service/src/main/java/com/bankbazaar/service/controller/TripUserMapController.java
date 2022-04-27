package com.bankbazaar.service.controller;


import com.bankbazaar.dto.model.TripUserMapDto;
import com.bankbazaar.service.service.TripUserMapService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        return tripUserMapService.getTrips(tripId,principal);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> addUsers( @RequestParam Long tripId,@RequestParam Long userId) {
        if(tripUserMapService.addUsers(tripId,userId)!=null) {

            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value="/delete",method = RequestMethod.POST)
    public String deleteUsers(@RequestParam Long tripId, @RequestParam Long userId) {
        tripUserMapService.deleteUsers(tripId,userId);
        return "redirect:/trip-user-mapping?tripId="+tripId;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<TripUserMapDto> updateUsers( @RequestParam Long tripId, @RequestParam Long userId, @RequestParam String role) {

        TripUserMapDto response = tripUserMapService.updateUsers(tripId, userId, role);
        if(response==null)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else
        {
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
    }
}
