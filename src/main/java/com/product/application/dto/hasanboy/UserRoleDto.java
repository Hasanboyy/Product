package com.product.application.dto.hasanboy;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRoleDto {
    private Integer id;
    @NotBlank(message = ("The path cannot be empty or null"))
    private String name;
}
