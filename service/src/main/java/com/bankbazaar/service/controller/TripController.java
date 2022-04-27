package com.bankbazaar.service.controller;

import com.bankbazaar.dto.model.TripDto;
import com.bankbazaar.service.manager.TripService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.security.Principal;


@Slf4j
@Controller
@RequestMapping("/trips")
public class TripController {

    @Autowired
    private TripService tripService;

    @RequestMapping(value = "/create",method = RequestMethod.GET)
    public String createTrip(){
        return "create_trip";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addTrip( @ModelAttribute TripDto trip, Principal principal) {
        tripService.addTrip(trip, principal);
        return "redirect:/trips";
    }
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView viewTrip(Principal principal){
        return tripService.viewTrip(principal);
    }

}
