package com.product.application.repository.hasanboy;

import com.product.application.model.hasanboy.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.awt.print.Pageable;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address,Integer>, JpaSpecificationExecutor<Address> {
    Optional<Address> findByIdAndDeletedAtIsNull(Integer id);
}
