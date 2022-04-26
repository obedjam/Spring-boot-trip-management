package com.bankbazaar.core.repository;

import com.bankbazaar.core.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    public Optional<UserEntity> findByEmail(String email);
}
