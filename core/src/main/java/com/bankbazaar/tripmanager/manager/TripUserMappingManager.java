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
     * @param trip_id
     * @param user_id
     */
    public TripUserMapping saveTripUserMapping(Long trip_id,Long user_id)
    {
        return tripUserMapRepository.save(new TripUserMapping(trip_id,user_id));
    }
    /**
     * Delete record by trip_id and user_id
     * @param trip_id
     * @param user_id
     */
    public Boolean deleteTripUserMapping(Long trip_id,Long user_id) {

        if(CheckData(trip_id, user_id).isPresent()) {
            tripUserMapRepository.deleteById(new TripUserCompositeKey(trip_id, user_id));
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

    private Optional<TripUserMapping> CheckData(Long trip_id,Long user_id)
    {
        return tripUserMapRepository.findById(new TripUserCompositeKey(trip_id, user_id));
    }
}
