package com.bankbazaar.tripmanager.repository;

import com.bankbazaar.tripmanager.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users,Long> {
}
