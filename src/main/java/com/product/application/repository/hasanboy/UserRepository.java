package com.product.application.repository.hasanboy;

import com.product.application.model.hasanboy.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
