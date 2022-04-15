package com.bankbazaar.tripmanager.manager;

import com.bankbazaar.tripmanager.model.TripUserCompositeKey;
import com.bankbazaar.tripmanager.model.TripUserMapping;
import com.bankbazaar.tripmanager.repository.TripUserMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class TripUserMappingManager {

    @Autowired
    private TripUserMappingRepository tripUserMapRepository;

    /**
     * Insert to TripUserMapping table
     * @param tripId
     * @param userId
     */
    public TripUserMapping saveTripUserMapping(Long tripId,Long userId)
    {
        return tripUserMapRepository.save(new TripUserMapping(tripId,userId));
    }
    /**
     * Delete record by trip_id and user_id
     * @param tripId
     * @param userId
     */
    public Boolean deleteTripUserMapping(Long tripId,Long userId) {

        if(CheckData(tripId, userId).isPresent()) {
            tripUserMapRepository.deleteById(new TripUserCompositeKey(tripId, userId));
            return true;
        }
        return false;
    }
    /**
     * Update record
     */
    public TripUserMapping updateTripUserMapping(TripUserMapping tripUserMap) {
        TripUserMapping presentData = CheckData(tripUserMap.getTripId(),tripUserMap.getUserId()).orElse(null);
        if(presentData!=null)
        {
            presentData.updateData(tripUserMap);
            return tripUserMapRepository.save(presentData);
        }
        return null;
    }

    private Optional<TripUserMapping> CheckData(Long tripId,Long userId)
    {
        return tripUserMapRepository.findById(new TripUserCompositeKey(tripId, userId));
    }
}
