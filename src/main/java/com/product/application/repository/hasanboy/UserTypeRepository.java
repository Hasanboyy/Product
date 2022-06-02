package com.product.application.repository.hasanboy;

import com.product.application.model.hasanboy.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserTypeRepository extends JpaRepository<UserType,Integer> {
    Optional<UserType> findByIdAndDeletedAtIsNull(Integer id);
}
