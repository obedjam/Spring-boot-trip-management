package com.bankbazaar.tripmanager.manager;

import com.bankbazaar.tripmanager.model.Trip;
import com.bankbazaar.tripmanager.model.TripActivity;
import com.bankbazaar.tripmanager.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class TripManager {
    @Autowired
    private TripRepository tripRepository;

    /**
     * Insert to Trip table
     * @param data
     */
    public Trip saveTrip(Trip data)
    {
        if(data.getTripId()==null) {
            return tripRepository.save(data);
        }
        else {
            Optional<Trip> presentData = exists(data.getTripId());
            if(presentData.isPresent())
            {
                Trip newData = updateData(presentData.get(),data);
                return tripRepository.save(newData);
            }
            return null;
        }
    }
    /**
     * Get record by ID
     */
    public Optional<Trip> getTripById(Long id) {
        return tripRepository.findById(id);
    }
    /**
     * Delete record by id
     *
     * @param id
     */
    public boolean deleteTrip(Long id) {
        if(exists(id).isPresent())
        {
            tripRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private Optional<Trip> exists(Long id)
    {
        return tripRepository.findById(id);
    }

    public Trip updateData(Trip presentData, Trip trip)
    {
        if(trip.getTripName()!=null)
        {
            presentData.setTripName(trip.getTripName());
        }
        if(trip.getTripDescription()!=null)
        {
            presentData.setTripDescription(trip.getTripDescription());
        }
        if(trip.getDestination()!=null)
        {
            presentData.setDestination(trip.getDestination());
        }
        if(trip.getStartDate()!=null)
        {
            presentData.setStartDate(trip.getStartDate());
        }
        if(trip.getEndDate()!=null)
        {
            presentData.setEndDate(trip.getEndDate());
        }
        return presentData;
    }
}
