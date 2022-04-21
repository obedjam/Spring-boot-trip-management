package com.bankbazaar.core.controller;

import com.bankbazaar.core.manager.TripActivityManager;
import com.bankbazaar.core.model.TripActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
@RestController
@RequestMapping("/trip-activity")
public class TripActivityController {
    @Autowired
    private TripActivityManager service;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<TripActivity> addTrip(@Valid @RequestBody TripActivity tripActivity) {

        TripActivity response = service.saveTripActivity(tripActivity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<TripActivity> findTripById(@RequestParam Long id) {

        Optional<TripActivity> tripData = service.getTripActivityById(id);
        if (tripData.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(tripData.get(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<TripActivity> deleteTrip(@RequestParam Long id) {

        if (!service.deleteTripActivity(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<TripActivity> updateTrip(@Valid @RequestBody TripActivity tripActivity) {

        TripActivity response = service.saveTripActivity(tripActivity);
        if(response == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
