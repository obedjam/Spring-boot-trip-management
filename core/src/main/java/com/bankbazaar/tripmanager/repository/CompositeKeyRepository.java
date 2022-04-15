package com.bankbazaar.tripmanager.repository;

import com.bankbazaar.tripmanager.model.TripUserCompositeKey;
import com.bankbazaar.tripmanager.model.TripUserMapping;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CompositeKeyRepository extends JpaRepository<TripUserMapping,TripUserCompositeKey> {
}
