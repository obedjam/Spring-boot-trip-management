package com.bankbazaar.tripmanager.manager;

import com.bankbazaar.tripmanager.model.TripUserCompositeKey;
import com.bankbazaar.tripmanager.model.TripUserMapping;
import com.bankbazaar.tripmanager.model.UserRole;
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
    public TripUserMapping saveTripUserMapping(Long tripId, Long userId, UserRole role)
    {
        return tripUserMapRepository.save(new TripUserMapping(tripId,userId,role));
    }
    /**
     * Delete record by trip_id and user_id
     * @param tripId
     * @param userId
     */
    public Boolean deleteTripUserMapping(Long tripId,Long userId) {

        if(checkData(tripId, userId).isPresent()) {
            tripUserMapRepository.deleteById(new TripUserCompositeKey(tripId, userId));
            return true;
        }
        return false;
    }
    /**
     * Update record
     */
    public TripUserMapping updateTripUserMapping(TripUserMapping tripUserMap) {
        Optional<TripUserMapping> presentData = checkData(tripUserMap.getTripId(),tripUserMap.getUserId());
        if(presentData.isPresent())
        {
            updateData(presentData.get(),tripUserMap);
            return tripUserMapRepository.save(presentData.get());
        }
        return null;
    }

    private Optional<TripUserMapping> checkData(Long tripId,Long userId)
    {
        return tripUserMapRepository.findById(new TripUserCompositeKey(tripId, userId));
    }

    public void updateData(TripUserMapping presentData, TripUserMapping tripUserMap)
    {
        if(tripUserMap.getUserRole()!=null)
        {
            presentData.setUserRole(tripUserMap.getUserRole());
        }
    }
}
