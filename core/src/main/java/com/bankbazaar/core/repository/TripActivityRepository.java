package com.bankbazaar.core.repository;

import com.bankbazaar.core.model.TripActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TripActivityRepository extends JpaRepository<TripActivityEntity, Long> {

    public List<TripActivityEntity> findAllByTripId(Long tripId);
}
