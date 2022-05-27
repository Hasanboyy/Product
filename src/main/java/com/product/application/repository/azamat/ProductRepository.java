package com.product.application.repository.azamat;

import com.product.application.model.azamat.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
