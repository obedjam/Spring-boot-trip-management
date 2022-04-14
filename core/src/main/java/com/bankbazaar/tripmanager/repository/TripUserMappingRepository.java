package com.bankbazaar.tripmanager.repository;

import com.bankbazaar.tripmanager.model.TripUserMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripUserMappingRepository extends JpaRepository<TripUserMapping,Long> {
}
