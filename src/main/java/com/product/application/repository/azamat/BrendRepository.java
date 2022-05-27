package com.product.application.repository.azamat;

import com.product.application.model.azamat.Brend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface BrendRepository extends JpaRepository<Brend,Integer>, JpaSpecificationExecutor<Brend> {
    Optional<Brend> findByIdAndDeletedAtIsNull(Integer id);
}
