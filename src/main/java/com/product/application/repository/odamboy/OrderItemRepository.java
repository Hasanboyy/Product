package com.product.application.repository.odamboy;

import com.product.application.model.odamboy.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer>, JpaSpecificationExecutor<OrderItem> {
    Optional<OrderItem> findByIdAndDeletedAtIsNull(Integer id);
}
