package com.product.application.filter.hasanboy;

import com.product.application.filter.FilterDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressFilter extends FilterDto {
    private String region;
    private String city;
    private String district;
    private String street;
    private Integer home;
}
