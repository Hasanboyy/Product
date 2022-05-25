package com.product.application.repository.hasanboy;

import com.product.application.model.hasanboy.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image,Integer>, JpaSpecificationExecutor<Image> {
    Optional<Image> findByIdAndDeletedAtIsNull(Integer id);
}
