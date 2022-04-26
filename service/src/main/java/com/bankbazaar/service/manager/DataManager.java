package com.bankbazaar.service.manager;

import com.bankbazaar.core.manager.TripEntityManager;
import com.bankbazaar.core.manager.TripUserMapEntityManager;
import com.bankbazaar.core.manager.UserEntityManager;
import com.bankbazaar.core.model.TripEntity;
import com.bankbazaar.core.model.TripUserMapEntity;
import com.bankbazaar.core.model.UserEntity;
import com.bankbazaar.dto.model.TripUserMapDto;
import com.bankbazaar.service.mapper.TripUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Slf4j
@Service
public class DataManager {

    @Autowired
    private UserEntityManager userEntityManager;

    @Autowired
    private TripEntityManager tripEntityManager;

    @Autowired
    private TripUserMapEntityManager tripUserMapEntityManager;

    @Autowired
    private TripUserMapper modelMapper;

    public void addAdmin(TripEntity trip, Principal principal)
    {
        TripUserMapDto tripUserMapping = new TripUserMapDto();
        tripUserMapping.setUserId(userDetails(principal).get().getUserId());
        tripUserMapping.setTripId(trip.getTripId());
        tripUserMapping.setUserRole("ADMIN");

        tripUserMapEntityManager.saveTripUserMapping(modelMapper.dtoToDomain(tripUserMapping));

    }

    public Optional<UserEntity> userDetails(Principal principal)
    {
        Optional<UserEntity> userData = userEntityManager.getUserByEmail(principal.getName());
        return userData;
    }

    public Optional<TripEntity> getTripDetails(Long tripId)
    {
        Optional<TripEntity> tripData = tripEntityManager.getTripById(tripId);
        return tripData;
    }

    public Optional<UserEntity> getUserDetails(Long userId)
    {
        Optional<UserEntity> userData = userEntityManager.getUserById(userId);
        return userData;
    }
}
