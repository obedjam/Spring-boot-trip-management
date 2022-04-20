package com.bankbazaar.tripmanager.repository;

import com.bankbazaar.tripmanager.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users,Long> {
    @Query("SELECT user FROM Users user WHERE user.email = ?1")
    public Optional<Users> findByEmail(String email);
}
