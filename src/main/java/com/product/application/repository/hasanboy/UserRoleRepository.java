package com.product.application.repository.hasanboy;

import com.product.application.model.hasanboy.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole,Integer> , JpaSpecificationExecutor<UserRole> {
    Optional<UserRole> findByIdAndDeletedAtIsNull(Integer id);
}
