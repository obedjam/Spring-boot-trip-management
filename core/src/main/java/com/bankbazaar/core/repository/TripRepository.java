package com.bankbazaar.core.repository;

import com.bankbazaar.core.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip,Long> {
}
