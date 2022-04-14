package com.bankbazaar.tripmanager.repository;

import com.bankbazaar.tripmanager.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip,Long> {
}
