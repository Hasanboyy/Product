package com.product.application.dto.hasanboy;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserTypeDto {
    private Integer id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
}
