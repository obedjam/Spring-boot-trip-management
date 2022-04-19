package com.bankbazaar.tripmanager.controller;

import com.bankbazaar.tripmanager.manager.TripActivityManager;
import com.bankbazaar.tripmanager.model.TripActivity;
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

    @RequestMapping(value="/get-trip-activity",method = RequestMethod.GET)
    public ResponseEntity<Optional<TripActivity>> findTripById(@RequestParam Long id) {

        Optional<TripActivity> tripData = service.getTripActivityById(id);
        if (tripData.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(tripData, HttpStatus.OK);
    }

    @RequestMapping(value="/delete-trip-activity",method = RequestMethod.DELETE)
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
