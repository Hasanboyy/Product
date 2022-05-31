package com.product.application.repository.azamat;

import com.product.application.model.azamat.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductTypeRepository extends JpaRepository<ProductType,Integer>, JpaSpecificationExecutor<ProductType> {
}
