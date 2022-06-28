package com.product.application.model.azamat;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter

@Entity
@Table(name = ("products"))
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer rate;
    private Boolean visible;
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = ("product_type_id"), insertable = false, updatable = false)
    private ProductType productType;
    @Column(name = ("product_type_id"))
    private Integer productTypeId;

    @Column(name = ("created_at"))
    private LocalDateTime createdAt;

    @Column(name = ("updated_at"))
    private LocalDateTime updatedAt;

    @Column(name = ("deleted_at"))
    private LocalDateTime deletedAt;
}
