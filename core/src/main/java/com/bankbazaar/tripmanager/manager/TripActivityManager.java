package com.bankbazaar.tripmanager.manager;

import com.bankbazaar.tripmanager.model.TripActivity;
import com.bankbazaar.tripmanager.repository.TripActivityRepository;
import org.springframework.beans.factory.ObjectProvider;
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
        if(checkData(id).isPresent())
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

        Optional<TripActivity> presentData = checkData(tripActivity.getTripId());
        if(presentData.isPresent())
        {
            TripActivity newData = updateData(presentData.get(), tripActivity);
            return tripActivityRepository.save(newData);

        }
        return null;
    }

    private Optional<TripActivity> checkData(Long id)
    {
        return tripActivityRepository.findById(id);
    }

    public TripActivity updateData(TripActivity presentData,TripActivity tripActivity)
    {
        if(tripActivity.getActivityStatus()!=null)
        {
            presentData.setActivityStatus(tripActivity.getActivityStatus());
        }
        if(tripActivity.getActivityDescription()!=null)
        {
            presentData.setActivityDescription(tripActivity.getActivityDescription());
        }
        if(tripActivity.getActivityTime()!=null)
        {
            presentData.setActivityTime(tripActivity.getActivityTime());
        }
        if(tripActivity.getLocation()!=null)
        {
            presentData.setLocation(tripActivity.getLocation());
        }

        return presentData;
    }
}