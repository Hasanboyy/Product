package com.product.application.model.hasanboy;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter

@Entity
@Table(name = ("users"))
public class  User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String surname;
    private String email;
    private String contact;
    private String password;
    private Boolean status;

    @OneToOne
    @JoinColumn(name = ("image"),insertable = false,updatable = false)
    private Image image;
    @Column(name = ("image_id"))
    private Integer imageId;

    @ManyToOne
    @JoinColumn(name = ("user_role"),insertable = false,updatable = false)
    private UserRole userRole;
    @Column(name = ("user_role_id"))
    private Integer userRoleId;

    @OneToOne
    @JoinColumn(name = ("address"),insertable = false,updatable = false)
    private Address address;
    @Column(name = ("adress_id"))
    private Integer addressId;

    @Column(name = ("created_at"))
    private LocalDateTime createdAt;
    @Column(name = ("updated_at"))
    private LocalDateTime updatedAt;
    @Column(name = ("deleted_at"))
    private LocalDateTime deletedAt;
}
