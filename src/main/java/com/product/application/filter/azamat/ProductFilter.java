package com.product.application.filter.azamat;

import com.product.application.filter.FilterDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductFilter extends FilterDto {
    private String name;
    private String surname;
    private String direc;
}
