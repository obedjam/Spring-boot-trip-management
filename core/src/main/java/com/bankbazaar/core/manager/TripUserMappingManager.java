package com.bankbazaar.core.manager;

import com.bankbazaar.core.model.TripUserMappingEntity;
import com.bankbazaar.core.repository.TripUserMappingRepository;
import com.bankbazaar.core.model.TripUserCompositeKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class TripUserMappingManager {

    @Autowired
    private TripUserMappingRepository tripUserMapRepository;

    /**
     * Insert to TripUserMapping table
     * @param data
     */
    public TripUserMappingEntity saveTripUserMapping(TripUserMappingEntity data)
    {
        if(data.getUserId()==null&&data.getTripId()==null) {
            return tripUserMapRepository.save(data);
        }
        else{
            Optional<TripUserMappingEntity> presentData = exists(data.getTripId().getTripId(),data.getUserId().getUserId());
            if(presentData.isPresent())
            {
                updateData(presentData.get(),data);
                return tripUserMapRepository.save(presentData.get());
            }
            return null;
        }
    }
    /**
     * Delete record by trip_id and user_id
     * @param tripId
     * @param userId
     */
    public Boolean deleteTripUserMapping(Long tripId,Long userId) {

        if(exists(tripId, userId).isPresent()) {
            tripUserMapRepository.deleteById(new TripUserCompositeKey(tripId, userId));
            return true;
        }
        return false;
    }

    private Optional<TripUserMappingEntity> exists(Long tripId, Long userId)
    {
        return tripUserMapRepository.findById(new TripUserCompositeKey(tripId, userId));
    }

    public void updateData(TripUserMappingEntity presentData, TripUserMappingEntity tripUserMap)
    {
        if(tripUserMap.getUserRole()!=null)
        {
            presentData.setUserRole(tripUserMap.getUserRole());
        }
    }
}
