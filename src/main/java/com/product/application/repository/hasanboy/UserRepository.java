package com.product.application.repository.hasanboy;

import com.product.application.model.hasanboy.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByIdAndDeletedAtIsNull(Integer id);
}
