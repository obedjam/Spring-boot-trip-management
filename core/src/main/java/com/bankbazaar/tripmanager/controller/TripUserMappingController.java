package com.bankbazaar.tripmanager.controller;

import com.bankbazaar.tripmanager.manager.TripUserMappingManager;
import com.bankbazaar.tripmanager.model.TripUserMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RestController
@RequestMapping("/trip-user-mapping")
public class TripUserMappingController {
    @Autowired
    private TripUserMappingManager service;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<TripUserMapping> addUsers(@Valid @RequestBody TripUserMapping tripUserMap) {

        TripUserMapping response = service.saveTripUserMapping(tripUserMap);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value="/delete-trip-map",method = RequestMethod.DELETE)
    public ResponseEntity<TripUserMapping> deleteUsers(@RequestParam Long tripId, @RequestParam Long userId) {

        if (!service.deleteTripUserMapping(tripId,userId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<TripUserMapping> updateUsers(@Valid @RequestBody TripUserMapping tripUserMap) {

        TripUserMapping response = service.saveTripUserMapping(tripUserMap);
        if(response == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
