package com.bankbazaar.core.repository;

import com.bankbazaar.core.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users,Long> {
    @Query("SELECT u FROM Users u WHERE u.email = ?1")
    public Optional<Users> findByEmail(String email);
}
