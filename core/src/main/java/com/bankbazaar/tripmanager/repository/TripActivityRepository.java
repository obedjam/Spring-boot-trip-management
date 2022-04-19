package com.bankbazaar.tripmanager.repository;

import com.bankbazaar.tripmanager.model.TripActivity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripActivityRepository extends JpaRepository<TripActivity, Long> {
}
