package com.bankbazaar.tripmanager.manager;

import com.bankbazaar.tripmanager.model.TripActivity;
import com.bankbazaar.tripmanager.repository.TripActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class TripActivityManager {
    @Autowired
    private TripActivityRepository tripActivityRepository;

    /**
     * Insert to TripActivity table
     * @param data
     */
    public TripActivity saveTripActivity(TripActivity data)
    {
        return tripActivityRepository.save(data);
    }
    /**
     * Get record by ID
     */
    public Optional<TripActivity> getTripActivityById(Long id) {
        return tripActivityRepository.findById(id);
    }
    /**
     * Delete record by id
     * @param id
     */
    public String deleteTripActivity(Long id) {
        if(tripActivityRepository.findById(id).isEmpty())
        {
            return null;
        }
        tripActivityRepository.deleteById(id);
        return "TripActivity "+id+" has been removed" ;
    }
    /**
     * Update record
     */
    public TripActivity updateTripActivity(TripActivity tripActivity) {
        if(tripActivityRepository.findById(tripActivity.getUserId()).isEmpty())
        {
            return null;
        }
        return tripActivityRepository.save(tripActivity);
    }
}
