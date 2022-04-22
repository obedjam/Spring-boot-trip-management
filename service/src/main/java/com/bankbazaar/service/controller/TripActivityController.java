package com.bankbazaar.service.controller;

import com.bankbazaar.core.manager.TripActivityManager;
import com.bankbazaar.core.model.TripActivityEntity;
import com.bankbazaar.dto.model.TripActivityDto;
import org.modelmapper.ModelMapper;
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

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<TripActivityEntity> addTrip(@Valid @RequestBody TripActivityDto tripActivity) {

        TripActivityEntity response = service.saveTripActivity(modelMapper.map(tripActivity, TripActivityEntity.class));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<TripActivityEntity> findTripById(@RequestParam Long id) {

        Optional<TripActivityEntity> tripData = service.getTripActivityById(id);
        if (tripData.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(tripData.get(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<TripActivityEntity> deleteTrip(@RequestParam Long id) {

        if (!service.deleteTripActivity(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<TripActivityEntity> updateTrip(@Valid @RequestBody TripActivityDto tripActivity) {

        TripActivityEntity response = service.saveTripActivity(modelMapper.map(tripActivity, TripActivityEntity.class));
        if(response == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
