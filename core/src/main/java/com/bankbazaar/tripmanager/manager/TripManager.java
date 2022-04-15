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
     * @param id
     */
    public String deleteTrip(Long id) {
        if(tripRepository.findById(id).isEmpty())
        {
            return null;
        }
        tripRepository.deleteById(id);
        return "Trip "+id+" has been removed" ;
    }
    /**
     * Update record
     */
    public Trip updateTrip(Trip trip) {
        if(tripRepository.findById(trip.getTripId()).isEmpty())
        {
            return null;
        }
        return tripRepository.save(trip);
    }
}
