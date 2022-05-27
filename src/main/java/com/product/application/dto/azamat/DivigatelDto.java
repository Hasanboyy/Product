package com.product.application.dto.azamat;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DivigatelDto {
    private Integer id;

   @NotNull
    private Double size;
}
