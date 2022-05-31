package com.product.application.repository.azamat;

import com.product.application.model.azamat.Divigatel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface DivigatelRepository extends JpaRepository<Divigatel,Integer>, JpaSpecificationExecutor<Divigatel> {
    Optional<Divigatel> findByIdAndDeletedAtIsNull(Integer id);
}
