package com.bankbazaar.service.controller;

import com.bankbazaar.core.manager.TripManager;
import com.bankbazaar.core.model.TripEntity;
import com.bankbazaar.dto.model.TripDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
@RestController
@RequestMapping("/trips")
public class TripController {
    @Autowired
    private TripManager service;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<TripEntity> addTrip(@Valid @RequestBody TripDto trip) {

        TripEntity response = service.saveTrip(modelMapper.map(trip, TripEntity.class));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<TripEntity> findTripById(@RequestParam Long id) {

        Optional<TripEntity> tripData = service.getTripById(id);
        if (tripData.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(tripData.get(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<TripEntity> deleteTrip(@RequestParam Long id) {

        if (!service.deleteTrip(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<TripEntity> updateTrip(@Valid @RequestBody TripDto trip) {

        TripEntity response = service.saveTrip(modelMapper.map(trip, TripEntity.class));
        if(response == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
