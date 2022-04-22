package com.bankbazaar.core.manager;

import com.bankbazaar.core.model.TripActivityEntity;
import com.bankbazaar.core.repository.TripActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class TripActivityManager {
    @Autowired
    private TripActivityRepository tripActivityRepository;

    /**
     * Insert to TripActivity table
     * @param data
     */
    public TripActivityEntity saveTripActivity(TripActivityEntity data)
    {
        if(data.getTripId()==null) {
            return tripActivityRepository.save(data);
        }
        else{
            Optional<TripActivityEntity> presentData = exists(data.getTripId().getTripId());
            if(presentData.isPresent())
            {
                updateData(presentData.get(), data);
                return tripActivityRepository.save(presentData.get());

            }
            return null;
        }
    }
    /**
     * Get record by ID
     */
    public Optional<TripActivityEntity> getTripActivityById(Long id) {
        return tripActivityRepository.findById(id);
    }
    /**
     * Delete record by id
     *
     * @param id
     */
    public boolean deleteTripActivity(Long id) {
        if(exists(id).isPresent())
        {
            tripActivityRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private Optional<TripActivityEntity> exists(Long id)
    {
        return tripActivityRepository.findById(id);
    }

    public void updateData(TripActivityEntity presentData, TripActivityEntity tripActivity)
    {
        if(tripActivity.getActivityStatus()!=null)
        {
            presentData.setActivityStatus(tripActivity.getActivityStatus());
        }
        if(!tripActivity.getActivityDescription().isBlank())
        {
            presentData.setActivityDescription(tripActivity.getActivityDescription());
        }
        if(tripActivity.getActivityTime()!=null)
        {
            presentData.setActivityTime(tripActivity.getActivityTime());
        }
        if(!tripActivity.getLocation().isBlank())
        {
            presentData.setLocation(tripActivity.getLocation());
        }
    }
}