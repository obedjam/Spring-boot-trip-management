package com.bankbazaar.tripmanager.manager;
import com.bankbazaar.tripmanager.model.TripUserCompositeKey;
import com.bankbazaar.tripmanager.model.TripUserMapping;
import com.bankbazaar.tripmanager.model.Users;
import com.bankbazaar.tripmanager.repository.CompositeKeyRepository;
import com.bankbazaar.tripmanager.repository.TripUserMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class TripUserMappingManager {
    @Autowired
    private TripUserMappingRepository tripUserMapRepository;

    @Autowired
    private CompositeKeyRepository compositeKeyRepository;

    /**
     * Insert to TripUserMapping table
     * @param data
     */
    public TripUserMapping saveTripUserMapping(TripUserMapping data)
    {
        return tripUserMapRepository.save(data);
    }
    /**
     * Get record by trip_id and user_id
     */
    public Optional<TripUserMapping> getTripUserMappingById(TripUserCompositeKey id) {
        return compositeKeyRepository.findById(new TripUserCompositeKey(id.getTripId(),id.getUserId()));
    }
    /**
     * Delete record by trip_id and user_id
     * @param id
     */
    public String deleteTripUserMapping(TripUserCompositeKey id) {
        if(compositeKeyRepository.findById(new TripUserCompositeKey(id.getTripId(),id.getUserId())).isEmpty())
        {
            return null;
        }
        compositeKeyRepository.deleteById(new TripUserCompositeKey(id.getTripId(),id.getUserId()));
        return "TripUserMapping has been removed" ;
    }
    /**
     * Update record
     */
    public TripUserMapping updateTripUserMapping(TripUserMapping tripUserMap) {
        if(tripUserMapRepository.findById(tripUserMap.getUserId()).isEmpty())
        {
            return null;
        }
        return tripUserMapRepository.save(tripUserMap);
    }
}
