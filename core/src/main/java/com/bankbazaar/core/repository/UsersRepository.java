package com.bankbazaar.core.repository;

import com.bankbazaar.core.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<UserEntity,Long> {
    @Query("SELECT u FROM UserEntity u WHERE u.email = ?1")
    public Optional<UserEntity> findByEmail(String email);
}
