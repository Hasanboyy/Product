package com.product.application.security.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ProfileEntity {
    private Integer id;
    private String userName;
    private String password;
    private Boolean enabled;
    private String role;

}
