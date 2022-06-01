package com.product.application.security.repository;

import com.product.application.security.model.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<ProfileEntity,Integer> {

    Optional<ProfileEntity> findByUserName(String username);
}
