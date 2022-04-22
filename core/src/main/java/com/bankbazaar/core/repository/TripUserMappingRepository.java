package com.bankbazaar.core.repository;

import com.bankbazaar.core.model.TripUserMappingEntity;
import com.bankbazaar.core.model.TripUserCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripUserMappingRepository extends JpaRepository<TripUserMappingEntity, TripUserCompositeKey> {
}
