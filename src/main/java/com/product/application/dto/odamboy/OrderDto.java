package com.product.application.dto.odamboy;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.product.application.model.hasanboy.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDto {

    private Integer id;
    @NotBlank(message = ("Invalid requirement"))
    private String requirement;
    @NotBlank(message = ("Invalid contact"))
    private String contact;
    @NotBlank(message = ("Invalid address "))
    private String address;
    @NotBlank(message = ("Invalid Total Payment "))
    private BigDecimal totalPayment;

    private User user;
    @NotBlank(message = ("Invalid user"))
    private Integer userId;

}
