package com.bankbazaar.core.repository;

import com.bankbazaar.core.model.TripActivityCompositeKey;
import com.bankbazaar.core.model.TripActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TripActivityRepository extends JpaRepository<TripActivityEntity, TripActivityCompositeKey> {

    @Query("SELECT t FROM TripActivityEntity t WHERE t.tripId.tripId = ?1")
    public List<TripActivityEntity> getByTripId(Long tripId);
}
