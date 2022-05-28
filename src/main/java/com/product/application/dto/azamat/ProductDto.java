package com.product.application.dto.azamat;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)

public class ProductDto {
    private Integer id;

    @NotBlank(message = "The name cannot empty")
    private String name;

    private String description;
    private BigDecimal price;
    private Integer rate;
    private Boolean visible;
    private Integer productType;
}
