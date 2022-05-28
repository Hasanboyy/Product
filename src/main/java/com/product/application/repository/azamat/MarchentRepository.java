package com.product.application.repository.azamat;

import com.product.application.model.azamat.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface MarchentRepository extends JpaRepository<Merchant,Integer>, JpaSpecificationExecutor<Merchant> {
    Optional<Merchant> findByIdAndDeletedAtIsNull(Integer id);
}
