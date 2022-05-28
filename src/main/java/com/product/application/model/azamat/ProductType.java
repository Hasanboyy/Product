package com.product.application.model.azamat;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter

@Entity
@Table(name = ("productTypes"))
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = ("merchant"), insertable = false, updatable = false)
    private Merchant merchant;
    @Column(name = ("merchant_id"))
    private Integer merchantId;

    @ManyToOne
    @JoinColumn(name = ("brend"), insertable = false, updatable = false)
    private Brend brend;
    @Column(name = ("brend_id"))
    private Integer brendId;

    @ManyToOne
    @JoinColumn(name = ("dvigatel"), insertable = false, updatable = false)
    private Divigatel divigatel;
    @Column(name = ("dvigatel_id"))
    private Integer dvigatelId;


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
