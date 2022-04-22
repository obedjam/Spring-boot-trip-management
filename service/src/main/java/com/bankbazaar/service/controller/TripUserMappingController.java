package com.bankbazaar.service.controller;

import com.bankbazaar.core.manager.TripUserMappingManager;
import com.bankbazaar.core.model.TripUserMappingEntity;
import com.bankbazaar.dto.model.TripUserMappingDto;
import org.modelmapper.ModelMapper;
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

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<TripUserMappingEntity> addUsers(@Valid @RequestBody TripUserMappingDto tripUserMap) {

        TripUserMappingEntity response = service.saveTripUserMapping(modelMapper.map(tripUserMap, TripUserMappingEntity.class));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<TripUserMappingEntity> deleteUsers(@RequestParam Long tripId, @RequestParam Long userId) {

        if (!service.deleteTripUserMapping(tripId,userId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<TripUserMappingEntity> updateUsers(@Valid @RequestBody TripUserMappingDto tripUserMap) {

        TripUserMappingEntity response = service.saveTripUserMapping(modelMapper.map(tripUserMap, TripUserMappingEntity.class));
        if(response == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
