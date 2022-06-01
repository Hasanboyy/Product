package com.product.application.repository.hasanboy;

import com.product.application.model.hasanboy.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<ProfileEntity,Integer> {
    Optional<ProfileEntity> findByUserName(String username);
}
