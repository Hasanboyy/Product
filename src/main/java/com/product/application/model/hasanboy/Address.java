package com.product.application.model.hasanboy;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {
    private Integer id;
    private String region;
    private String city;
    private String district;//Tuman
    private String street;
    private Integer home;
}
