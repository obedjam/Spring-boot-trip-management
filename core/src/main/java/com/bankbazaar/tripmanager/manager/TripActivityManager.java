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
     *
     * @param id
     */
    public boolean deleteTripActivity(Long id) {
        if(CheckData(id).isPresent())
        {
            tripActivityRepository.deleteById(id);
            return true;
        }
        return false;
    }
    /**
     * Update record
     */
    public TripActivity updateTripActivity(TripActivity tripActivity) {

        TripActivity presentData = CheckData(tripActivity.getTripId()).orElse(null);
        if(presentData!=null)
        {
            presentData.updateData(tripActivity);
            return tripActivityRepository.save(presentData);

        }
        return null;
    }

    private Optional<TripActivity> CheckData(Long id)
    {
        return tripActivityRepository.findById(id);
    }
}