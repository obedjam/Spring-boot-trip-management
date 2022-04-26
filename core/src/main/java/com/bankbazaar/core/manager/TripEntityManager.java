package com.bankbazaar.core.manager;

import com.bankbazaar.core.model.TripEntity;
import com.bankbazaar.core.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class TripEntityManager {
    @Autowired
    private TripRepository tripRepository;

    /**
     * Insert to Trip table
     * @param data
     */
    public TripEntity saveTrip(TripEntity data)
    {
        if(data.getTripId()==null) {
            return tripRepository.save(data);
        }
        else {
            Optional<TripEntity> presentData = exists(data.getTripId());
            if(presentData.isPresent())
            {
                TripEntity newData = updateData(presentData.get(),data);
                return tripRepository.save(newData);
            }
            return null;
        }
    }
    /**
     * Get record by ID
     */
    public Optional<TripEntity> getTripById(Long id) {
        return tripRepository.findById(id);
    }

    private Optional<TripEntity> exists(Long id)
    {
        return tripRepository.findById(id);
    }

    private TripEntity updateData(TripEntity presentData, TripEntity trip)
    {
        TripEntity newData = new TripEntity();
        newData.setTripId(presentData.getTripId());
        if(!trip.getTripName().isBlank())
        {
            newData.setTripName(trip.getTripName());
        }
        else {newData.setTripName(presentData.getTripName());}
        if(!trip.getTripDescription().isBlank())
        {
            newData.setTripDescription(trip.getTripDescription());
        }
        else{newData.setTripDescription(presentData.getTripDescription());}
        if(!trip.getDestination().isBlank())
        {
            newData.setDestination(trip.getDestination());
        }
        else{newData.setDestination(presentData.getDestination());}
        if(trip.getStartDate()!=null)
        {
            newData.setStartDate(trip.getStartDate());
        }
        else{newData.setStartDate(presentData.getStartDate());}
        if(trip.getEndDate()!=null)
        {
            newData.setEndDate(trip.getEndDate());
        }
        else{newData.setEndDate(presentData.getEndDate());}
        return newData;
    }
}
