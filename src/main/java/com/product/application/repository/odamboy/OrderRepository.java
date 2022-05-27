package com.product.application.repository.odamboy;

import com.product.application.model.odamboy.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Integer>, JpaSpecificationExecutor<Order> {

    Optional<Order> findByIdAndDeletedAtIsNull(Integer id);
}
