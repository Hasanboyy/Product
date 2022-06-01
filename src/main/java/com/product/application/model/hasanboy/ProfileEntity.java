package com.product.application.model.hasanboy;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter

@Entity
@Table(name = ("profiles"))
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = ("username"))
    private String username;
    @Column(name = ("password"))
    private String password;
    @Column(name = ("enabled"))
    private Boolean enabled;
    @Column(name = ("role"))
    private String role;

    @Override
    public String toString() {
        return "ProfileEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", role='" + role + '\'' +
                '}';
    }
}
