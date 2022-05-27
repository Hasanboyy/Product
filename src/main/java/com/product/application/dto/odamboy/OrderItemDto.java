package com.product.application.dto.odamboy;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.product.application.model.azamat.Product;
import com.product.application.model.odamboy.Order;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderItemDto {

    private Integer id;

    private Product product;
    @NotBlank(message = ("Invalid product"))
    private Integer productId;

    private Order order;
    @NotBlank(message = "Invalid order")
    private Integer orderId;

}
