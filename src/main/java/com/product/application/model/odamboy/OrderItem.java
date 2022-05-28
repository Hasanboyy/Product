package com.product.application.model.odamboy;

import com.product.application.model.azamat.Product;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter

@Entity
@Table(name = ("order_item"))
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = ("product"), insertable = false, updatable = false)
    private Product product;
    @Column(name = ("product_id"))
    private Integer productId;

    @ManyToOne
    @JoinColumn(name = ("orders"), insertable = false, updatable = false)
    private Order order;
    @Column(name = ("order_id"))
    private Integer orderId;

    @Column(name = ("created_at"))
    private LocalDateTime createdAt;
    @Column(name = ("update_at"))
    private LocalDateTime updateAt;
    @Column(name = ("deleted_at"))
    private LocalDateTime deletedAt;
}
