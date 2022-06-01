package com.product.application.security.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProfileDetailDto {
    private String userName;
    private String role;
    private String token;
}
