package com.product.application.repository.hasanboy;

import com.product.application.model.hasanboy.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address,Integer> {
    Optional<Address> findByIdAndDeletedAtIsNull(Integer id);
}
