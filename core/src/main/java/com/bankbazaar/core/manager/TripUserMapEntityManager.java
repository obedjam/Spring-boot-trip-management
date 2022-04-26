package com.bankbazaar.core.manager;

import com.bankbazaar.core.model.TripEntity;
import com.bankbazaar.core.model.TripUserMapEntity;
import com.bankbazaar.core.model.UserEntity;
import com.bankbazaar.core.repository.TripRepository;
import com.bankbazaar.core.repository.TripUserMapRepository;
import com.bankbazaar.core.model.TripUserCompositeKey;
import com.bankbazaar.core.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class TripUserMapEntityManager {

    @Autowired
    private TripUserMapRepository tripUserMapRepository;
    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private UserRepository usersRepository;

    /**
     * Insert to TripUserMapping table
     * @param data
     */
    public TripUserMapEntity saveTripUserMapping(TripUserMapEntity data)
    {
        Optional<TripEntity> tripId = tripRepository.findById(data.getTripId().getTripId());
        Optional<UserEntity> userId = usersRepository.findById(data.getUserId().getUserId());
        data.setTripId(tripId.get());
        data.setUserId(userId.get());
        return tripUserMapRepository.save(data);
    }
    /**
     * Update to TripUserMapping table
     * @param data
     */
    public TripUserMapEntity updateTripUserMapping(TripUserMapEntity data)
    {
            Optional<TripUserMapEntity> presentData = exists(data.getTripId().getTripId(),data.getUserId().getUserId());
            if(presentData.isPresent())
            {
                TripUserMapEntity newData = updateData(presentData.get(),data);
                return tripUserMapRepository.save(newData);
            }
            return null;
    }
    /**
     * get all trips using userId
     * @param userId
     */
    public List<TripUserMapEntity> getTripsUserId(Long userId) {

        return tripUserMapRepository.getByUserId(userId);
    }
    /**
     * get all trips using tripId
     * @param tripId
     */
    public List<TripUserMapEntity> getTripsTripId(Long tripId) {

        return tripUserMapRepository.getByTripId(tripId);
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


    public Optional<TripUserMapEntity> exists(Long tripId, Long userId)
    {
        return tripUserMapRepository.findById(new TripUserCompositeKey(tripId, userId));
    }

    private TripUserMapEntity updateData(TripUserMapEntity presentData, TripUserMapEntity tripUserMap)
    {
        TripUserMapEntity newData = new TripUserMapEntity();
        newData.setTripId(presentData.getTripId());
        newData.setUserId(presentData.getUserId());
        if(tripUserMap.getUserRole()!=null)
        {
            newData.setUserRole(tripUserMap.getUserRole());
        }
        else {newData.setUserRole(presentData.getUserRole());}
        return newData;
    }
}
