package com.product.application.dto.hasanboy;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserTypeDto {
    private Integer id;
    @NotBlank(message = ("The Name cannot be empty or null"))
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
}
