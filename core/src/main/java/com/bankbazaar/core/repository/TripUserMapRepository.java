package com.bankbazaar.core.repository;

import com.bankbazaar.core.model.TripUserMapEntity;
import com.bankbazaar.core.model.TripUserCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TripUserMapRepository extends JpaRepository<TripUserMapEntity, TripUserCompositeKey> {
    public List<TripUserMapEntity> findAllByUserIdUserId(Long userId);
    public List<TripUserMapEntity> findAllByTripIdTripId(Long tripId);
}
