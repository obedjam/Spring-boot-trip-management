package com.bankbazaar.core.repository;

import com.bankbazaar.core.model.TripUserMapEntity;
import com.bankbazaar.core.model.TripUserCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TripUserMapRepository extends JpaRepository<TripUserMapEntity, TripUserCompositeKey> {
    @Query("SELECT t FROM TripUserMapEntity t WHERE t.userId.userId = ?1")
    public List<TripUserMapEntity> getByUserId(Long userId);

    @Query("SELECT t FROM TripUserMapEntity t WHERE t.tripId.tripId = ?1")
    public List<TripUserMapEntity> getByTripId(Long tripId);
}
