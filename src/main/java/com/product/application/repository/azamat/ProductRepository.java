package com.product.application.repository.azamat;

import com.product.application.model.azamat.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Integer>, JpaSpecificationExecutor<Product> {
    Optional<Product> findByIdAndDeletedAtIsNull(Integer id);
}
