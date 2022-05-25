package com.product.application.model.hasanboy;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = ("users"))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String status;
    @OneToOne
    @JoinColumn(name = ("image"))
    private Image image;
    @Column(name = ("image_id"))
    private Integer imageId;
    @ManyToOne
    @JoinColumn(name = ("user_role"))
    private UserRole userRole;
    @Column(name = ("user_role_id"))
    private Integer userRoleId;

    private Address address;
    private Integer addressId;
}
