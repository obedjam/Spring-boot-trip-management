package com.bankbazaar.tripmanager.controller;

import com.bankbazaar.tripmanager.manager.TripManager;
import com.bankbazaar.tripmanager.model.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
@RestController
@RequestMapping("/trip")
public class TripController {
    @Autowired
    private TripManager service;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Trip> addTrip(@Valid @RequestBody Trip trip) {

        Trip response = service.saveTrip(trip);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value="/get-trip",method = RequestMethod.GET)
    public ResponseEntity<Optional<Trip>> findTripById(@RequestParam Long id) {

        Optional<Trip> tripData = service.getTripById(id);
        if (tripData.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(tripData, HttpStatus.OK);
    }

    @RequestMapping(value="/delete-trip",method = RequestMethod.DELETE)
    public ResponseEntity<Trip> deleteTrip(@RequestParam Long id) {

        if (!service.deleteTrip(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Trip> updateTrip(@Valid @RequestBody Trip trip) {

        Trip response = service.saveTrip(trip);
        if(response == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
