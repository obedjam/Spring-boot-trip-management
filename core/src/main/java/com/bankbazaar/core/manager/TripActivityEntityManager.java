package com.bankbazaar.core.manager;

import com.bankbazaar.core.model.TripActivityCompositeKey;
import com.bankbazaar.core.model.TripActivityEntity;
import com.bankbazaar.core.model.TripEntity;
import com.bankbazaar.core.repository.TripActivityRepository;
import com.bankbazaar.core.repository.TripRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class TripActivityEntityManager {
    @Autowired
    private TripActivityRepository tripActivityRepository;

    @Autowired
    private TripRepository tripRepository;

    /**
     * Insert to TripActivity table
     * @param data
     */
    public TripActivityEntity saveTripActivity(TripActivityEntity data)
    {
        Optional<TripEntity> tripId = tripRepository.findById(data.getTripId().getTripId());
        data.setTripId(tripId.get());
        return tripActivityRepository.save(data);
    }

    /**
     * update to TripActivity table
     * @param data
     */
    public TripActivityEntity updateTripActivity(TripActivityEntity data)
    {
        Optional<TripActivityEntity> presentData = exists(data.getTripId().getTripId(),data.getActivityId());
        if(presentData.isPresent())
        {
            TripActivityEntity newData = updateData(presentData.get(), data);
            return tripActivityRepository.save(newData);

        }
        return null;
    }
    /**
     * Get record by ID
     */
    public Optional<TripActivityEntity> getTripActivityById(Long tripId, Long activityId) {
        return tripActivityRepository.findById(new TripActivityCompositeKey(tripId,activityId));
    }

    /**
     * get all activities using tripId
     * @param tripId
     */
    public List<TripActivityEntity> getActivityTripId(Long tripId) {

        return tripActivityRepository.getByTripId(tripId);
    }

    private Optional<TripActivityEntity> exists(Long tripId, Long activityId)
    {
        return tripActivityRepository.findById(new TripActivityCompositeKey(tripId,activityId));
    }
    private TripActivityEntity updateData(TripActivityEntity presentData, TripActivityEntity tripActivity)
    {
        TripActivityEntity newData = new TripActivityEntity();
        newData.setActivityId(presentData.getActivityId());
        newData.setTripId(presentData.getTripId());
        newData.setAddedBy(presentData.getAddedBy());
        if(tripActivity.getActivityStatus()!=null)
        {
            newData.setActivityStatus(tripActivity.getActivityStatus());
        }
        else{newData.setActivityStatus(presentData.getActivityStatus());}
        if(tripActivity.getActivityDescription()!=null)
        {
            newData.setActivityDescription(tripActivity.getActivityDescription());
        }
        else{newData.setActivityDescription(presentData.getActivityDescription());}
        if(tripActivity.getActivityTime()!=null)
        {
            newData.setActivityTime(tripActivity.getActivityTime());
        }
        else {newData.setActivityTime(presentData.getActivityTime());}
        if(tripActivity.getLocation()!=null)
        {
            newData.setLocation(tripActivity.getLocation());
        }
        else {newData.setLocation(presentData.getLocation());}
        return newData;
    }
}