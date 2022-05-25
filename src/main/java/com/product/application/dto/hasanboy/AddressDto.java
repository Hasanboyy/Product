package com.product.application.dto.hasanboy;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressDto {
    private Integer id;
    @NotBlank(message = ("The region cannot be empty or null"))
    private String region; //Mintaqa
    @NotBlank(message = ("The region cannot be empty or null"))
    private String city; //Shahar
    @NotBlank(message = ("The region cannot be empty or null"))
    private String district; //Tuman
    @NotBlank(message = ("The region cannot be empty or null"))
    private String street; //Ko`cha
    //@Size(min = 3,max = 10,message = ("The number size of the house should be 3 to 10 times"))
    private Integer home;
}
