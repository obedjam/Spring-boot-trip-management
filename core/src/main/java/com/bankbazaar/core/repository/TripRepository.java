package com.bankbazaar.core.repository;

import com.bankbazaar.core.model.TripEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<TripEntity,Long> {
}
