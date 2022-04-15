package com.bankbazaar.tripmanager.manager;

import com.bankbazaar.tripmanager.model.Trip;
import com.bankbazaar.tripmanager.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class TripManager {
    @Autowired
    private TripRepository tripRepository;

    /**
     * Insert to Trip table
     * @param data
     */
    public Trip saveTrip(Trip data)
    {
        return tripRepository.save(data);
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
        if(CheckData(id).isPresent())
        {
            tripRepository.deleteById(id);
            return true;
        }
        return false;
    }
    /**
     * Update record
     */
    public Trip updateTrip(Trip trip) {

        Trip presentData = CheckData(trip.getTripId()).orElse(null);
        if(presentData!=null)
        {
            presentData.updateData(trip);
            return tripRepository.save(presentData);
        }
        return null;
    }

    private Optional<Trip> CheckData(Long id)
    {
        return tripRepository.findById(id);
    }
}
